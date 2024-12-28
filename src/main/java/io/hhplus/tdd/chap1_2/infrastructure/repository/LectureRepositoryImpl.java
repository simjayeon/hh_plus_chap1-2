package io.hhplus.tdd.chap1_2.infrastructure.repository;

import io.hhplus.tdd.chap1_2.domain.repository.LectureRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.hhplus.tdd.chap1_2.domain.entity.lecture.Lecture;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static io.hhplus.tdd.chap1_2.entity.lecture.QLecture.lecture;
import static io.hhplus.tdd.chap1_2.entity.userinfo.QUserInfo.userInfo;

public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LectureRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Lecture> findAllByLectureDate(String lectureDate) {
        LocalDate date = LocalDate.parse(lectureDate);
        LocalDateTime dateTime = date.atStartOfDay();

        return queryFactory
                .select(lecture)
                .from(lecture)
                .leftJoin(lecture.lectureDate)
                .on(lecture.lectureDate.any().lectureDate.eq(dateTime))
                .leftJoin(lecture.userInfo, userInfo)
                .on(userInfo.id.eq(lecture.userInfo.id))
                .fetch();
    }
}