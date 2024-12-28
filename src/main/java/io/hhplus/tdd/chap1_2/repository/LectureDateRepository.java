package io.hhplus.tdd.chap1_2.repository;

import io.hhplus.tdd.chap1_2.entity.lecture.LectureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectureDateRepository extends JpaRepository<LectureDate, Long> {
}
