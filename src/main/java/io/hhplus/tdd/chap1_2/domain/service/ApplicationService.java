package io.hhplus.tdd.chap1_2.domain.service;

import io.hhplus.tdd.chap1_2.Interfaces.dto.ReqApplicationLectureDto;
import io.hhplus.tdd.chap1_2.domain.entity.lecture.Lecture;
import io.hhplus.tdd.chap1_2.domain.entity.lecture.LectureDate;
import io.hhplus.tdd.chap1_2.domain.entity.userinfo.ApplicationLog;
import io.hhplus.tdd.chap1_2.domain.entity.userinfo.UserInfo;
import io.hhplus.tdd.chap1_2.enums.ApplicationStateType;
import io.hhplus.tdd.chap1_2.infrastructure.jpa.ApplicationLogRepository;
import io.hhplus.tdd.chap1_2.infrastructure.jpa.LectureDateRepository;
import io.hhplus.tdd.chap1_2.infrastructure.jpa.LectureRepository;
import io.hhplus.tdd.chap1_2.infrastructure.jpa.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationLogRepository applicationLogRepository;
    private final LectureDateRepository lectureDateRepository;
    private final LectureRepository lectureRepository;
    private final UserInfoRepository userInfoRepository;

    public ResponseEntity<ApplicationStateType> setApplicationLecture(ReqApplicationLectureDto req) {
        Lecture lecture = lectureRepository.findById(req.getLectureId()).orElse(null);
        LectureDate lectureDate = lectureDateRepository.findById(req.getLectureDateId()).orElse(null);
        UserInfo userInfo = userInfoRepository.findById(req.getUserId()).orElse(null);

        // 특강 신청 가능 여부 확인
        List<ApplicationLog> applicationLogList = applicationLogRepository.findApplicationLogsByUserInfoAndLectureId(userInfo, req.getLectureId());

        if (!CollectionUtils.isEmpty(applicationLogList)) {
            // 로그 기록
            ApplicationLog applicationLog = new ApplicationLog();
            applicationLog.setLecture(lecture);
            applicationLog.setLectureDate(lectureDate);
            applicationLog.setUserInfo(userInfo);
            applicationLog.setState(ApplicationStateType.FAILED.name());
            applicationLog.setApplicationDate(LocalDateTime.now());
            applicationLogRepository.save(applicationLog);
            return ResponseEntity.ok(ApplicationStateType.FAILED);
        }

        // 로그 기록
        ApplicationLog applicationLog = new ApplicationLog();
        applicationLog.setLecture(lecture);
        applicationLog.setLectureDate(lectureDate);
        applicationLog.setUserInfo(userInfo);
        applicationLog.setState(ApplicationStateType.SUCCESS.name());
        applicationLog.setApplicationDate(LocalDateTime.now());
        applicationLogRepository.save(applicationLog);

        // 특강 수강 정보 조회
        Optional<LectureDate> lectureDateDto = lectureDateRepository.findById(req.getLectureDateId());
        if (lectureDateDto.isEmpty()) {
            return ResponseEntity.ok(ApplicationStateType.FAILED);
        }

        // 특강 신청자 카운트 업데이트
        LectureDate lectureDateUpdate = lectureDateDto.get();
        lectureDateUpdate.setApplicationUserCount(lectureDate.getApplicationUserCount() + 1);

        if (lectureDate.getApplicationUserCount() > 30) {
            return ResponseEntity.ok(ApplicationStateType.FAILED);
        } else if (lectureDate.getApplicationUserCount() == 30) {
            lectureDate.setApplicationAvailableYn(false);
        }
        lectureDateRepository.save(lectureDate);
        return ResponseEntity.ok(ApplicationStateType.SUCCESS);
    }
}
