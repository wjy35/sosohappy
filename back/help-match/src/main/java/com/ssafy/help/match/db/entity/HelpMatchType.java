package com.ssafy.help.match.db.entity;

public enum HelpMatchType {
    None("none"),
    Single("single");

    private final String type;

    HelpMatchType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
