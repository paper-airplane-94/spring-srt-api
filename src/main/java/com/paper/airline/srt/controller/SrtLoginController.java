package com.paper.airline.srt.controller;

import com.paper.airline.srt.dto.CookieDto;
import com.paper.airline.srt.dto.LoginDto;
import com.paper.airline.srt.service.SrtLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SrtLoginController {
    private final SrtLoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<CookieDto> login(LoginDto loginDto) throws IOException {
        CookieDto cookieDto = loginService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Set-Cookie", cookieDto.toString())
                .body(cookieDto);
    }
}
