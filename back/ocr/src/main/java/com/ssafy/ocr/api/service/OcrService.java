package com.ssafy.ocr.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.*;

@Service
@RequiredArgsConstructor
public class OcrService {

    private final ObjectMapper objectMapper;

    @Value("${ocr.api.url}")
    private String apiURL;

    @Value("${ocr.secret.key}")
    private String secretKey;

    private Map<String,String> jsonParse(String text) {

        Map<String,String> result = new HashMap<>();
        JsonNode root = null;

        try {
            root = objectMapper.readTree(text);
            String documentRegistrationNumber = root.path("images").
                    get(0).path("fields").
                    get(0).path("subFields").
                    get(0).path("inferText").asText();

            String name = root.path("images").
                    get(0).path("fields").
                    get(1).path("subFields").
                    get(0).path("inferText").asText();

            result.put("documentRegistrationNumber",documentRegistrationNumber);
            result.put("name",name);

        } catch(Exception e) {
            e.getMessage(); // throw Error
        }

        return result;
    }
    private String naverOcrApi(String base64EncodeFile, String ext) {

        String apiURL = this.apiURL;
        String secretKey = this.secretKey;
        String result = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", ext);
            image.put("data", base64EncodeFile); // image should be public, otherwise, should use data
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            result = response.toString();
            br.close();

        } catch (Exception e) {
            e.getMessage();
        }

        return result;
    }
    public String base64Encoding(MultipartFile multipartFile) throws IOException {

        byte[] bytes = multipartFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
