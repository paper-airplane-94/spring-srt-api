package com.paper.airline.srt.dto;

import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ScheduleDto {
    private String departStationCode;
    private String departTime;
    private String arriveStationCode;
    private String arriveTime;

    @Builder
    public ScheduleDto(String departStationCode, String departTime, String arriveStationCode, String arriveTime) {
        this.departStationCode = departStationCode;
        this.departTime = departTime;
        this.arriveStationCode = arriveStationCode;
        this.arriveTime = arriveTime;
    }
}
