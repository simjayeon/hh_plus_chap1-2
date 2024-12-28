package io.hhplus.tdd.chap1_2.Interfaces.dto;

import io.hhplus.tdd.chap1_2.domain.entity.lecture.LectureDate;
import io.hhplus.tdd.chap1_2.enums.ApplicationAvailableType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class LectureDateDto {

    private Long lectureDateId;
    private LocalDateTime lectureDate;
    private LocalDateTime deadlineDate;
    private ApplicationAvailableType availableYn;
    private Integer applicationUserCount;

    public static LectureDateDto of (List<LectureDate> lectureDateList) {
        return LectureDateDto.builder()
                .lectureDateId(lectureDateList.get(0).getId())
                .lectureDate(lectureDateList.get(0).getLectureDate())
                .deadlineDate(lectureDateList.get(0).getDeadlineDate())
                .availableYn(ApplicationAvailableType.UNAVAILABLE)
                .applicationUserCount(lectureDateList.get(0).getApplicationUserCount())
                .build();
    }
}
