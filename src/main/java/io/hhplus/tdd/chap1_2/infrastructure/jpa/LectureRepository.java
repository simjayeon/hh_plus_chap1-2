package io.hhplus.tdd.chap1_2.infrastructure.jpa;

import io.hhplus.tdd.chap1_2.domain.entity.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAllByLectureDate(String lectureDate);
}
