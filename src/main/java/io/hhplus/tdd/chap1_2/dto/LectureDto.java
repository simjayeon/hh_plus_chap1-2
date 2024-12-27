package io.hhplus.tdd.chap1_2.dto;

import io.hhplus.tdd.chap1_2.service.ApplicationAvailableType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class LectureDto {

    private Long id;
    private String lectureTitle;
    private Long lecturerId;
    private String lectureName;
    private Long lectureDateId;
    private Date lectureDate;
    private ApplicationAvailableType availableYn;

}
