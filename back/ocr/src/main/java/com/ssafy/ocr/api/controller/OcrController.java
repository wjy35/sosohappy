package com.ssafy.ocr.api.controller;


import com.ssafy.ocr.api.exception.CustomException;
import com.ssafy.ocr.api.response.FormattedResponse;
import com.ssafy.ocr.api.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.ssafy.ocr.api.exception.ErrorCode.CRAWLING_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;
    @PostMapping("/send")
    public ResponseEntity<FormattedResponse> checkImage(@RequestParam(value = "file")MultipartFile multipartFile) {

        String documentNumber = ocrService.checkImage(multipartFile);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("확인되었습니다.")
                .result("documentNumber", documentNumber)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }
    @PostMapping("/check")
    public ResponseEntity<FormattedResponse> checkDocument(String documentNumber, String name) {
        boolean isValidDocument;

        try {
            isValidDocument = ocrService.checkDocument(documentNumber, name);
        } catch (InterruptedException e) {
            throw new CustomException(CRAWLING_SERVER_ERROR);
        }

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("확인되었습니다.")
                .result("isValidDocument",isValidDocument)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }
}
