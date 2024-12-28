package io.hhplus.tdd.chap1_2.dto;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ReqApplicationLectureDto {

    private Long userId;
    private Long lectureId;
    private Long lectureDateId;

}
