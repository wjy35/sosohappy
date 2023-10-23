package com.ssafy.monster.controller;

import com.ssafy.monster.common.response.FormattedResponse;
import com.ssafy.monster.domain.req.LevelUpReq;
import com.ssafy.monster.domain.res.MonsterRes;
import com.ssafy.monster.service.MonsterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/collection")
    public FormattedResponse searchMonsterList(){
        //멤버 전달받기
        Long memberId = 1L;

        Map<String, Object> result = monsterService.searchMonsterList(memberId);

        return FormattedResponse.builder()
                .status("success")
                .message("Successfully loaded the monster list")
                .result(result).build();
    }

    @PatchMapping("/level-up")
    public FormattedResponse updateMonsterClover(@RequestBody LevelUpReq dto){
        //memberMonsterId, clover를 전달

        log.info("controller_updateMonsterClover_start -> memberMonsterId: " + dto.getMemberMonsterId() + "clover : " + dto.getClover());
        Map<String, Object> result = monsterService.updateMonsterClover(dto.getMemberMonsterId(), dto.getClover());

        return FormattedResponse.builder()
                .status("success")
                .message("Successfully reflected the clover")
                .result(result).build();
    }

}
