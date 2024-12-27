package io.hhplus.tdd.chap1_2.repository;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import io.hhplus.tdd.chap1_2.entity.userinfo.ApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long> {

    List<ApplicationLog> findApplicationLogsByUserId(Long userId);
}
