package com.paper.airline.srt.service;

import com.paper.airline.srt.dto.ScheduleDto;
import com.paper.airline.srt.dto.ScheduleRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.jsoup.Connection.Method;

import static com.paper.airline.srt.SrtApiConstants.SRT_SCHEDULE_URL;

@Slf4j
@Component
public class SrtScheduleService {

    public List<ScheduleDto> findSrtSchedules(Cookie cookie, ScheduleRequestDto requestDto) throws IOException {
        Document document = Jsoup.connect(SRT_SCHEDULE_URL)
                .timeout(60000)
                .method(Method.POST)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .cookie(cookie.getName(), cookie.getValue())
                .data("dptRsStnCd", requestDto.getDepartStationCode())
                .data("arvRsStnCd", requestDto.getArriveStationCode())
                .data("stlbTrnClsfCd", requestDto.getTrainTypeCode())
                .data("psgNum", requestDto.getPassengerTypeCode())
                .data("seatAttCd", requestDto.getSeatTypeCode())
                .data("isRequest", requestDto.getRequestTypeCode())
                .data("dptDt", requestDto.getDepartDate())
                .data("dptTm", requestDto.getDepartTime())
                .data("chtnDvCd", requestDto.getCourseTypeCode())
                .data("trnGpCd", requestDto.getTrainGpCode())
                .get();

        return parseScheduleTables(document).stream()
                .map(this::parseScheduleFromElement)
                .collect(Collectors.toList());
    }

    private Elements parseScheduleTables(Document document) {
        return document.getElementsByClass("tbl_wrap th_thead")
                .get(0)
                .getElementsByTag("tbody")
                .get(0)
                .getElementsByTag("tr");
    }

    private ScheduleDto parseScheduleFromElement(Element scheduleTableData) {
        Elements scheduleElements = scheduleTableData.getElementsByTag("td");
        return ScheduleDto.builder()
                .departStationCode(scheduleElements.get(3)
                        .getElementsByTag("div")
                        .get(0)
                        .text())
                .departTime(scheduleElements.get(3)
                        .getElementsByClass("time")
                        .get(0)
                        .text())
                .arriveStationCode(scheduleElements.get(4)
                        .getElementsByTag("div")
                        .get(0)
                        .text())
                .arriveTime(scheduleElements.get(4)
                        .getElementsByClass("time")
                        .get(0)
                        .text())
                .build();
    }
}
