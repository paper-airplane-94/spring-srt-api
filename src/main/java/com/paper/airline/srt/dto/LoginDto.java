package com.paper.airline.srt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginDto {
    private String loginTypeCode = "3";
    private String phoneNumber;
    private String password;

    @Builder
    public LoginDto(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
