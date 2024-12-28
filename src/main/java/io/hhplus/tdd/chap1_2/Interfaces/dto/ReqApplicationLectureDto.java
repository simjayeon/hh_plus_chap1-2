package io.hhplus.tdd.chap1_2.Interfaces.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReqApplicationLectureDto {

    private Long userId;
    private Long lectureId;
    private Long lectureDateId;

}
