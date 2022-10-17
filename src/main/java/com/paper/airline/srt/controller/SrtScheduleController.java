package com.paper.airline.srt.controller;

import com.paper.airline.srt.dto.ScheduleDto;
import com.paper.airline.srt.dto.ScheduleRequestDto;
import com.paper.airline.srt.service.SrtScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class SrtScheduleController {
    private final SrtScheduleService scheduleService;

    @GetMapping("/schedules")
    public List<ScheduleDto> findSchedules(@CookieValue(value = "JSESSIONID_ETK") Cookie cookie,
                                           ScheduleRequestDto requestDto) throws IOException {
        return scheduleService.findSrtSchedules(cookie, requestDto);
    }
}
