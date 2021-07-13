package com.example.ReceiptScanner.Services;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class ReceiptService {
    public static HttpURLConnection upload(File image) throws IOException {
        URL path = new URL("https://api.imgur/3/image");
        HttpURLConnection conn = (HttpURLConnection) path.openConnection();

        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

        byte[] b = new byte[(int) image.length()];
        FileInputStream fs = new FileInputStream(image);
        fs.read(b);
        fs.close();
        String base64 = URLEncoder.encode(DatatypeConverter.printBase64Binary(b),"UTF-8");

        writer.write("image=" + base64);
        writer.flush();
        writer.close();

        return conn;

    }
}
