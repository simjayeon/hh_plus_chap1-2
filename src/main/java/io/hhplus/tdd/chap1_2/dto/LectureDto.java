package io.hhplus.tdd.chap1_2.dto;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class LectureDto {

    private Long id;
    private String lectureTitle;
    private Long lecturerId;
    private String lectureName;
    private List<LectureDateDto> lectureDateList;

    public static LectureDto of (Lecture lecture, List<LectureDateDto> lectureDateDtos) {
        return LectureDto.builder()
                .id(lecture.getId())
                .lectureTitle(lecture.getLectureTitle())
                .lecturerId(lecture.getUserInfo().getId())
                .lectureName(lecture.getUserInfo().getUserName())
                .lectureDateList(lectureDateDtos)
                .build();
    }
}
