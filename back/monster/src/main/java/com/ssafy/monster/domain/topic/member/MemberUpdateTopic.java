package com.ssafy.monster.domain.topic.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "before",
        "after",
        "source",
        "op",
        "ts_ms",
        "transaction"
})
public class MemberUpdateTopic {

    @JsonProperty("before")
    public Before before;
    @JsonProperty("after")
    public After after;
    @JsonProperty("source")
    public Source source;
    @JsonProperty("op")
    public String op;
    @JsonProperty("ts_ms")
    public Timestamp tsMs;
    @JsonProperty("transaction")
    public Object transaction;

}