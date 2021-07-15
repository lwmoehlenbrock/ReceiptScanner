package com.example.ReceiptScanner.Services;

import com.example.ReceiptScanner.Model.Item;
import com.example.ReceiptScanner.Model.Receipt;
import com.example.ReceiptScanner.Model.User;
import com.example.ReceiptScanner.Repositories.ItemRepository;
import com.example.ReceiptScanner.Repositories.ReceiptRepository;
import com.example.ReceiptScanner.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.*;

import com.google.gson.*;

@Service
public class ReceiptService {

    Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    ItemRepository itemRepository;

    public Receipt scanReceipt(MultipartFile image, Long userId) throws Exception {
        String link = upload(image);
        String receiptText = getOCRText(link);
        logger.info(receiptText);
        return parseOCRText(receiptText, userId);
    }

    public Optional<Receipt> findReceiptById(Long id){
        return receiptRepository.findById(id);
    }

    public String getAllReceipts(){
        List<Receipt> receipts = receiptRepository.findAll();
        for(Receipt receipt:receipts){
            logger.info(receipt.getItemList().toString());
        }

        return receiptRepository.findAll().toString();
    }


    public String upload(MultipartFile image) throws IOException {
        URL path = new URL("https://api.imgur.com/3/image");
        HttpURLConnection conn = (HttpURLConnection) path.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID " + System.getenv("IMGUR_KEY"));
        conn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

        byte[] b = image.getBytes();

        String base64 = URLEncoder.encode(DatatypeConverter.printBase64Binary(b), StandardCharsets.UTF_8);

        writer.write("image=" + base64);
        writer.flush();
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonObject address = new JsonParser().parse(reader).getAsJsonObject().get("data").getAsJsonObject();
        var linkJson = address.get("link");
        String link = linkJson.getAsString();

        return link;
    }

    public String getOCRText(String link) throws Exception {
        URL path = new URL("https://api.ocr.space/parse/image");
        /*HttpClient conn = HttpClients.createDefault();
        HttpPost post = new HttpPost(link);
        post.setParams();*/

        HttpURLConnection conn = (HttpURLConnection) path.openConnection();
        conn.setRequestMethod("POST");
        //conn.setRequestProperty("apikey", "478342692b88957");
        //conn.setRequestProperty("isTable", "true");

        JSONObject postDataParams = new JSONObject();
        postDataParams.put("apikey", System.getenv("OCR_KEY"));
        postDataParams.put("url", link);
        postDataParams.put("isTable", "true");
        postDataParams.put("language", "eng");

        conn.setDoOutput(true);
        DataOutputStream writer = new DataOutputStream(conn.getOutputStream());

        //writer.write("language=eng");
        //writer.flush();
        writer.writeBytes(getPostDataString(postDataParams));
        writer.flush();
        //writer.write("isTable=true");
        //writer.flush();
        //writer.write("isTable=false");
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonObject address = new JsonParser().parse(reader).getAsJsonObject().get("ParsedResults").getAsJsonArray().get(0).getAsJsonObject();
        var linkJson = address.get("ParsedText");
        return linkJson.toString();
    }

    public Receipt parseOCRText(String data, Long userId){
        LinkedHashMap<String,String> temp = new LinkedHashMap<>();
        char[] dataChar = data.toCharArray();
        StringBuilder builder = new StringBuilder();
        boolean left = true;
        // If there are multiple tab characters, that means the receipt has multiple different parts of the item field.
        // Assuming the price will always be right before \t\r\n, we put all strings between tabs that aren't followed
        // by \t\r\n together as the item name
        boolean mid = false;
        String lastKey = "";
        String value = "";
        for(int i = 0; i < dataChar.length; i++){
            //\t represents some type of separation between text on the receipt, potentially between item and price
            if(dataChar[i] == '\\' && dataChar[i+1] == 't'){
                //If this is the first \t, it is safe to assume that this is the item identifier and not the price
                if(left){
                    //if left is true and the next two characters are \r\n then this is a line with only one entry so we should skip it
                    if(dataChar[i+2] == '\\' && dataChar[i+3] == 'r' && dataChar[i+4] == '\\' && dataChar[i+5] == 'n'){
                        left = true;
                        mid = false;
                        i+=5;
                        continue;
                    }
                    //The string on the left of the first \t on each line will become the key for the item HashMap
                    lastKey = builder.toString();
                    //We are no longer on the first String of text on this line of the receipt, so set left to false
                    left = false;
                }
                else{
                    //If left is false, then we are either looking at a string in the middle of the receipt line or the price
                    //mid = true is used for appending value to the key of the HashMap
                    //For example, a receipt entry such as "Salmon - Sashimi 17.00" would look like "Salmon\t- Sashimi\t17.00\t\r\n"
                    //So when we get to Sashimi, set mid to true
                    value = builder.toString();
                    mid = true;
                }
                //\t represents the end of each string, so we need to reset builder to be empty for the next string
                builder = new StringBuilder();
                //If the next two characters are \r and \n, then we are at the end of the line and we assume the String we are looking at
                //is the price, so value is holding the price String and we can add this item to the HashMap
                if(dataChar[i+2] == '\\' && dataChar[i+3] == 'r' && dataChar[i+4] == '\\' && dataChar[i+5] == 'n'){
                    temp.put(lastKey,value);
                    //reset left and mid and increment i so that we don't look at \r or \n within this loop
                    left = true;
                    mid = false;
                    i+=5;
                    //Since we are at the end of the line, we don't want to execute the rest of this loop iteration
                    continue;
                }
                //If the next two characters aren't \r\n, then we are looking at part of the item name, so we append
                //it to the key of the HashMap
                else if(mid){
                    StringBuilder keyBuilder = new StringBuilder(lastKey);
                    keyBuilder.append(value);
                    lastKey = keyBuilder.toString();
                }
                //We are still in the \t if statement, so since we are at the end of a String, we need to skip the
                //builder.append statement below
                i++;
                continue;
            }
            //If the character isn't \t, it's part of either the item name or price, so append it to the builder.
            builder.append(dataChar[i]);

        }
        double total = 0;
        HashMap<String,Double> items = new HashMap<>();
        //There are some cases where the right side of the receipt doesn't contain text, such as the business name or date,
        //so if there are any alphabet characters (excluding s in the case of OCR misreading a $) we will mark that pair of the
        //HashMap as notAnItem so that it doesn't get added to the itemized part of the receipt
        String charactersForFilter = "abcdefghijklmnopqrtuvwxyzABCDEFGHIJKLMNOPQRTUVWXYZ:/#";

        //Various interpretations that OCR might find when reading "Total"
        String[] totals = {"Total", "Tota1", "Tota 1", "total", "tota 1", "TOTAL", "TOTA1", "TOTA 1", "tota1"};
        String[] subTotals = {"subtotal", "Sub Total", "Subtotal", "SubTotal", "sub total", "Sub "};
        boolean notAnItem = false;
        boolean missingTotal = false;

        for(Map.Entry<String, String> entry:temp.entrySet()){
            String key = entry.getKey();
            String price = entry.getValue();
            char[] priceChar = price.toCharArray();
            boolean isTotal = false;
            boolean isSubTotal = false;

            if (price.isEmpty()){
                continue;
            }

            //skip over subtotal field
            for (String string:subTotals){
                if(key.contains(string)){
                    isSubTotal = true;
                }
            }
            if(isSubTotal){
                continue;
            }

            for(String string:totals){
                if(key.contains(string)){
                    isTotal = true;
                }
            }

            StringBuilder priceFilter = new StringBuilder();
            //Look at each character in right side of the receipt and if its an alphabet character, then we aren't looking
            //at a price so we skip this entry in the HashMap
            for(char character:priceChar){
                if(charactersForFilter.indexOf(character) >= 0){
                    notAnItem = true;
                    break;
                }
                //OCR occasionally inserts a space in the price part of some receipts and some will have a $
                //We don't want these characters in the price string since we will be parsing it to a double
                //so we skip the append statement and continue to the next character
                if(character == ' ' || character == '$'){
                    continue;
                }
                //Sometimes OCR will read a comma instead of a decimal point
                if(character == ','){
                    character = '.';
                }
                priceFilter.append(character);
            }
            if(notAnItem){
                notAnItem = false;
                continue;
            }
            //We don't want to add the total to the itemized list, it will be its own field in a Receipt object
            if(isTotal){
                total = Double.parseDouble(priceFilter.toString());
            }
            else {
                items.put(key, Double.parseDouble(priceFilter.toString()));
            }

        }

        //if there was no total field on the receipt or OCR missed it, manually sum up the items for the receipt
        if (total == 0){
            missingTotal = true;
        }
        ArrayList<Item> itemList = new ArrayList<Item>();

        for(Map.Entry<String, Double> entry:items.entrySet()){
            Item item = new Item();
            item.setName(entry.getKey());
            item.setCost(entry.getValue());
            item.setType("TBD");
            itemList.add(item);
            if(missingTotal){
                total += entry.getValue();
            }
        }


        logger.info(itemList.toString());

        Receipt receipt = new Receipt();
        User user = userRepository.getById(userId);
        receipt.setUser(user);
        receipt.setItemList(itemList);
        receipt.setTotal(total);
        receiptRepository.save(receipt);
        for(Item item:itemList){
            itemRepository.save(item);
        }
        logger.info(receiptRepository.findAll().toString());
        user.addReceipt(receipt);
        return receipt;



    }

    //Shamelessly taken from https://github.com/bsuhas/OCRTextRecognitionAndroidApp/blob/be7bb24a0e880cf174de9f16047fcb1b8c7447c6/app/src/main/java/com/ocrtextrecognitionapp/OCRAsyncTask.java
    //This is what allows us to send multiple parameters through the post request to the OCR API
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
