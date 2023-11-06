package com.ssafy.help.match.socket.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.help.match.socket.dto.CategoryDTO;
import lombok.Data;

@Data
public class HelpMatchRequest {
    @JsonProperty("memberId")
    Long memberId;

    @JsonProperty("nickname")
    String nickname;

    @JsonProperty("category")
    CategoryDTO category;

    @JsonProperty("latitude")
    Double latitude;

    @JsonProperty("longitude")
    Double longitude;

    @JsonProperty("content")
    String content;

    @JsonProperty("place")
    String place;
}