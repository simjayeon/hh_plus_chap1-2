package io.hhplus.tdd.chap1_2.repository;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findLectureByLectureDateId(Long lectureDateId);
}
