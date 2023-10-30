package com.ssafy.monster.domain.topic.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "version",
        "connector",
        "name",
        "ts_ms",
        "snapshot",
        "db",
        "sequence",
        "table",
        "server_id",
        "gtid",
        "file",
        "pos",
        "row",
        "thread",
        "query"
})
public class Source {

    @JsonProperty("version")
    public String version;
    @JsonProperty("connector")
    public String connector;
    @JsonProperty("name")
    public String name;
    @JsonProperty("ts_ms")
    public Timestamp tsMs;
    @JsonProperty("snapshot")
    public String snapshot;
    @JsonProperty("db")
    public String db;
    @JsonProperty("sequence")
    public Object sequence;
    @JsonProperty("table")
    public String table;
    @JsonProperty("server_id")
    public Long serverId;
    @JsonProperty("gtid")
    public Object gtid;
    @JsonProperty("file")
    public String file;
    @JsonProperty("pos")
    public Long pos;
    @JsonProperty("row")
    public Long row;
    @JsonProperty("thread")
    public Object thread;
    @JsonProperty("query")
    public Object query;

}
