package io.hhplus.tdd.chap1_2.domain.repository;

import io.hhplus.tdd.chap1_2.domain.entity.userinfo.ApplicationLog;

import java.util.List;

public interface ApplicationLogRepositoryCustom {
    List<ApplicationLog> findApplicationLogsByUserIdAndState(Long userId, String state);

    List<ApplicationLog> findApplicationLogsByUserInfoAndLectureId(Long userId, Long lectureId);

    List<ApplicationLog> findApplicationLogsByUserInfo(Long userId);

    List<ApplicationLog> findApplicationLogsByLectureId(Long lectureId);
}
