package com.example.ReceiptScanner.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.*;

@Service
public class ReceiptService {
    public static String upload(MultipartFile image) throws IOException {
        URL path = new URL("https://api.imgur.com/3/image");
        HttpURLConnection conn = (HttpURLConnection) path.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID 337b98313ab0735");

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
}
