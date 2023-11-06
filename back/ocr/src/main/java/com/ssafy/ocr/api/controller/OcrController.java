package com.ssafy.ocr.api.controller;


import com.ssafy.ocr.api.response.FormattedResponse;
import com.ssafy.ocr.api.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;
    @PostMapping("/send")
    public ResponseEntity<FormattedResponse> checkImage(@RequestParam(value = "file")MultipartFile multipartFile) {

        ocrService.checkImage(multipartFile);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("확인되었습니다.")
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }
}
