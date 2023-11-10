package com.ssafy.ocr.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.ocr.api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.*;

import static com.ssafy.ocr.api.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class OcrService {

    private final ObjectMapper objectMapper;
    private final String regex = "\\d.*";
    private final Pattern pattern = Pattern.compile(regex);
    private final String[] keywords = {"문","서","확","인","번","호"};
    private Matcher matcher;

    private final WebDriver driver;

    @Value("${ocr.api.url}")
    private String apiURL;

    @Value("${ocr.secret.key}")
    private String secretKey;

    @Value("${DISABLED_CONFIRM_URL}")
    private String baseURL;


    public String checkImage(MultipartFile multipartFile) {

        List<String> result = new ArrayList<>();
        String originalFileName = multipartFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String base64EncodeFile = null;
        String documentNumber = null;

        try {
            base64EncodeFile = base64Encoding(multipartFile);
            String text = naverOcrApi(base64EncodeFile,ext);
            result = jsonParse(text);
            documentNumber = getDocumentNumber(result);

        }
        catch (Exception e) {
            throw new CustomException(INVALID_IMAGE_ERROR);
        }

        return documentNumber;

    }

    public boolean checkDocument(String documentNumber, String name) throws InterruptedException {
        if(documentNumber == null || name == null) throw new CustomException(EMPTY_DOCUMENT_INFO);
        String[] parts = documentNumber.split("-");

        driver.get(baseURL);

        try{

            WebElement target = driver.findElement(By.cssSelector("#doc_ref_no1"));
            target.sendKeys(parts[0]);
            target = driver.findElement(By.cssSelector("#doc_ref_no2"));
            target.sendKeys(parts[1]);
            target = driver.findElement(By.cssSelector("#doc_ref_no3"));
            target.sendKeys(parts[2]);
            target = driver.findElement(By.cssSelector("#doc_ref_no4"));
            target.sendKeys(parts[3]);

            target = driver.findElement(By.cssSelector("#form2 > p > span.ibtn.large.navy > a"));
            target.click();

            target = driver.findElement(By.cssSelector("#doc_ref_key"));
            target.sendKeys(name);

            target = driver.findElement(By.cssSelector("#form1 > p > span.ibtn.large.navy > a"));
            target.click();

            target = driver.findElement(By.cssSelector("#form1 > table > tbody > tr > td:nth-child(1)"));

            if(target.getText().equals("장애인증명서 발급")) {
                return true;
            } else {
                throw new CustomException(INVALID_DOCUMENT_ERROR);
            }
        } catch (Exception e) {
            throw new CustomException(INVALID_DOCUMENT_ERROR);
        }

    }



    private List<String> jsonParse(String text) throws JsonProcessingException {

        List<String> result = new ArrayList<>();
        JsonNode root = null;

        root = objectMapper.readTree(text);

        JsonNode fields = root.get("images").get(0).get("fields");

        for (JsonNode field : fields) {
            String inferText = field.get("inferText").asText();
            result.add(inferText);
        }

        return result;
    }

    private String getDocumentNumber(List<String> result) {

        /**
         * 클라이언트 : 사진을 정방향으로 보내도록 유도한다.
         *
         * 사진이 정방향으로 업로드가 되었을 때 "장애인 증명서"에서 가장 먼저 확인하는 지표가 문서확인번호이다.
         *
         *  1. 숫자로 시작하고 "-"를 포함하며 19자리 숫자인 경우 == 문서확인번호가 명확하다.
         *      장애인 증명서에는 문서확인 번호 외에 "-"를 포함한 정보가 없다.
         *
         *  2. 문서확인번호 중 한 글자라도 포함하며, 길이가 19자리 "이상"인 경우
         *      1. 숫자가 처음 나타나는 위치를 찾고 그 위치에서 숫자의 끝까지 결과를 출력한다.
         */

        for (String s : result) {

            if (s.charAt(0) >= '0' && s.charAt(0) <= '9' && s.contains("-") && s.length() == 19) {
                return s;
            }

            for (String keyword : keywords) {
                if (s.contains(keyword) && s.length() >= 19) {
                    s = s.replace(" ","");
                    matcher = pattern.matcher(s);
                    if (matcher.find() && matcher.group().length() == 19) {
                        return matcher.group();
                    }
                }
            }
        }

        return null;
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

        } catch (IOException e) {
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }

        return result;
    }
    public String base64Encoding(MultipartFile multipartFile) throws IOException {

        byte[] bytes = multipartFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
