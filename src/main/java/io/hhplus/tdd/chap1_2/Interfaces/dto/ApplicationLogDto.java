package io.hhplus.tdd.chap1_2.Interfaces.dto;

import io.hhplus.tdd.chap1_2.domain.entity.userinfo.ApplicationLog;
import io.hhplus.tdd.chap1_2.enums.ApplicationStateType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ApplicationLogDto {

    private Long id;
    private Long userId;
    private String applicationUserName;
    private Long lectureId;
    private String lectureTitle;
    private Long lectureDateId;
    private LocalDateTime lectureDate;
    private ApplicationStateType state;
    private LocalDateTime applicationDate;

    public static ApplicationLogDto of (ApplicationLog applicationLog) {
        return ApplicationLogDto.builder()
                .id(applicationLog.getId())
                .lectureId(applicationLog.getLecture().getId())
                .lectureTitle(applicationLog.getLecture().getLectureTitle())
                .lectureDateId(applicationLog.getLectureDate().getLectureId())
                .lectureDate(applicationLog.getLectureDate().getLectureDate())
                .userId(applicationLog.getUserInfo().getId())
                .applicationUserName(applicationLog.getUserInfo().getUserName())
                .state(ApplicationStateType.valueOf(applicationLog.getState()))
                .applicationDate(applicationLog.getApplicationDate())
                .build();
    }

}
