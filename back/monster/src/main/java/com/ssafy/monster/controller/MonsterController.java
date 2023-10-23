package com.ssafy.monster.controller;

import com.ssafy.monster.common.response.FormattedResponse;
import com.ssafy.monster.domain.res.MonsterRes;
import com.ssafy.monster.service.MonsterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MonsterController {

    private final MonsterServiceImpl monsterService;

    @Autowired
    public MonsterController(MonsterServiceImpl monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/my")
    public FormattedResponse searchRepresentativeMonster(){
        //멤버 전달받기
        Long memberId = 1L;
        Map<String, Object> result = monsterService.searchRepresentativeMonster(memberId);

        return FormattedResponse.builder()
                .status("success")
                .message("Successfully loaded the representative monster")
                .result(result).build();
    }
}
