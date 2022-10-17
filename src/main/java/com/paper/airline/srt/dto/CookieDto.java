package com.paper.airline.srt.dto;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

public class CookieDto extends HashMap<String, String> {
    @Builder
    public CookieDto(Map<String, String> cookies) {
        super(cookies);
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        for (Map.Entry<String, String> entry : super.entrySet())
            stb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        return stb.toString();
    }
}
