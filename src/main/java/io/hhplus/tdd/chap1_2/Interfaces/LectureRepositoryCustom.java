package io.hhplus.tdd.chap1_2.Interfaces;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;

import java.util.List;

public interface LectureRepositoryCustom {

    List<Lecture> findAllByLectureDate(String lectureDate);
}
