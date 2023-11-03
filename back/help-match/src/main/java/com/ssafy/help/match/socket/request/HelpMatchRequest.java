package com.ssafy.help.match.socket.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.help.match.socket.dto.CategoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class HelpMatchRequest {
    @JsonProperty("memberId")
    Long memberId;

    @JsonProperty("nickname")
    String nickname;

    @JsonProperty("categoryList")
    List<CategoryDTO> categoryList;

    @JsonProperty("latitude")
    Double latitude;

    @JsonProperty("longitude")
    Double longitude;

    @JsonProperty("content")
    String content;

    @JsonProperty("place")
    String place;
}