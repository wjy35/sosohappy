package com.ssafy.help.match.api.controller;

import com.ssafy.help.match.api.response.FormattedResponse;
import com.ssafy.help.match.socket.mapper.FortuneCookieMapper;
import com.ssafy.help.match.socket.response.FortuneCookieItem;
import com.ssafy.help.match.socket.service.FortuneCookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FortuneCookieController {
    private final FortuneCookieService fortuneCookieService;

    @GetMapping("/fortune-cookie/list")
    ResponseEntity<FormattedResponse> list(@RequestHeader Long memberId){
        List<FortuneCookieItem> fortuneCookieItemList = FortuneCookieMapper.INSTANCE
                .toItem(fortuneCookieService.viewList(memberId));

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS GET LIST")
                .result("fortuneCookieList",fortuneCookieItemList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
