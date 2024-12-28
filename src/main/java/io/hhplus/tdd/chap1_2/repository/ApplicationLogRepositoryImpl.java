package io.hhplus.tdd.chap1_2.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.hhplus.tdd.chap1_2.Interfaces.ApplicationLogRepositoryCustom;
import io.hhplus.tdd.chap1_2.entity.userinfo.ApplicationLog;
import jakarta.persistence.EntityManager;

import java.util.List;

import static io.hhplus.tdd.chap1_2.entity.userinfo.QApplicationLog.applicationLog;
import static io.hhplus.tdd.chap1_2.entity.userinfo.QUserInfo.userInfo;

public class ApplicationLogRepositoryImpl implements ApplicationLogRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ApplicationLogRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ApplicationLog> findApplicationLogsByUserIdAndState(Long userId, String state) {

        return queryFactory
                .selectFrom(applicationLog)
                .join(applicationLog.userInfo, userInfo).fetchJoin()
                .where(applicationLog.userInfo.id.eq(userId)
                        .and(applicationLog.state.eq(state)))
                .fetch();
    }

    @Override
    public List<ApplicationLog> findApplicationLogsByUserInfoAndLectureId(Long userId, Long lectureId) {

        return queryFactory
                .selectFrom(applicationLog)
                .join(applicationLog.userInfo, userInfo).fetchJoin()
                .join(applicationLog.lecture)
                .on(applicationLog.lecture.id.eq(lectureId))
                .where(applicationLog.userInfo.id.eq(userId))
                .fetch();
    }

    @Override
    public List<ApplicationLog> findApplicationLogsByUserInfo(Long userId) {

        return queryFactory
                .selectFrom(applicationLog)
                .join(applicationLog.userInfo, userInfo)
                .where(applicationLog.userInfo.id.eq(userId))
                .fetch();
    }

    @Override
    public List<ApplicationLog> findApplicationLogsByLectureId(Long lectureId) {

        return queryFactory
                .selectFrom(applicationLog)
                .join(applicationLog.lecture)
                .on(applicationLog.lecture.id.eq(lectureId))
                .fetch();
    }
}