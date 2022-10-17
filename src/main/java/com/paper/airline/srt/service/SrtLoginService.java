package com.paper.airline.srt.service;

import com.paper.airline.srt.dto.CookieDto;
import com.paper.airline.srt.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.jsoup.Connection.Method;

import static com.paper.airline.srt.SrtApiConstants.SRT_LOGIN_URL;

@Slf4j
@Component
public class SrtLoginService {

    public CookieDto login(LoginDto loginDto) throws IOException {
        Connection.Response response = Jsoup.connect(SRT_LOGIN_URL)
                .timeout(60000)
                .method(Method.POST)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .data("srchDvCd", loginDto.getLoginTypeCode())
                .data("srchDvNm", loginDto.getPhoneNumber())
                .data("hmpgPwdCphd", loginDto.getPassword())
                .execute();

        return CookieDto.builder().cookies(response.cookies()).build();
    }
}
