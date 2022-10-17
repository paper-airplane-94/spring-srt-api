package com.paper.airline.srt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ScheduleRequestDto {
    private String courseTypeCode = "1";
    private String arriveTypeCode = "N";
    private String seatTypeCode = "015";
    private String passengerTypeCode = "1";
    private String trainGpCode = "109";
    private String trainTypeCode = "05";
    private String requestTypeCode = "Y";

    private String departDate;
    private String departTime;
    private String departStationCode;
    private String arriveStationCode;

    @Builder
    public ScheduleRequestDto(String departDate, String departTime,
                              String departStationCode, String arriveStationCode) {
        this.departDate = departDate;
        this.departTime = departTime;
        this.departStationCode = departStationCode;
        this.arriveStationCode = arriveStationCode;
    }
}
