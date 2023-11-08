package com.ssafy.monster.controller;

import com.ssafy.monster.common.response.FormattedResponse;
import com.ssafy.monster.domain.req.LevelUpReq;
import com.ssafy.monster.domain.res.CloverRes;
import com.ssafy.monster.domain.res.MonsterRes;
import com.ssafy.monster.service.MonsterService;
import com.ssafy.monster.service.MonsterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/my")
    public ResponseEntity<FormattedResponse> searchRepresentativeMonster(@RequestHeader Long memberId){

        MonsterRes monsterRes = monsterService.searchRepresentativeMonster(memberId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("대표 몬스터를 성공적으로 불러왔습니다.")
                .result("monster", monsterRes)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @GetMapping("/collection")
    public ResponseEntity<FormattedResponse> searchMonsterList(@RequestHeader Long memberId){

        List<MonsterRes> monsterResList = monsterService.searchMonsterList(memberId);
        boolean isMaxLevel = monsterService.checkMaxLevel(memberId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("보유중인 몬스터를 성공적으로 불러왔습니다.")
                .result("monsterList", monsterResList)
                .result("isMaxLevel",isMaxLevel)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @GetMapping("/clover")
    public ResponseEntity<FormattedResponse> searchCloverInfo(@RequestHeader Long memberId){

        CloverRes cloverRes = monsterService.searchCloverInfo(memberId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("보유중인 클로버를 성공적으로 불러왔습니다.")
                .result("clover", cloverRes)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @PatchMapping("/level-up")
    public ResponseEntity<FormattedResponse> updateMonsterClover(@RequestHeader Long memberId, @RequestBody LevelUpReq dto){
        //memberMonsterId, clover를 전달
        MonsterRes monsterRes = monsterService.updateMonsterClover(dto.getMemberMonsterId(), dto.getClover());
        CloverRes cloverRes = monsterService.searchCloverInfo(memberId);
        boolean isMaxLevel = monsterService.checkMaxLevel(memberId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("클로버를 성공적으로 반영하였습니다.")
                .result("clover", cloverRes)
                .result("monster", monsterRes)
                .result("isMaxLevel", isMaxLevel)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }


    @PatchMapping("/update/{profileMonsterId}")
    public ResponseEntity<FormattedResponse> updateMonsterProfile(@RequestHeader Long memberId, @PathVariable int profileMonsterId){
        monsterService.updateMemberMonsterProfile(memberId, profileMonsterId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("프로필을 성공적으로 변경하였습니다.")
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }


}
