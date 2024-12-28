package io.hhplus.tdd.chap1_2.domain.repository;

import io.hhplus.tdd.chap1_2.domain.entity.lecture.Lecture;

import java.util.List;

public interface LectureRepositoryCustom {

    List<Lecture> findAllByLectureDate(String lectureDate);
}
