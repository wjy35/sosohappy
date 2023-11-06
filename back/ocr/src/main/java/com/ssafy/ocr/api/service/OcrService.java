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

    public String base64Encoding(MultipartFile multipartFile) throws IOException {

        byte[] bytes = multipartFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
