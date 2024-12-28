package io.hhplus.tdd.chap1_2.service;

import io.hhplus.tdd.chap1_2.dto.ReqApplicationLectureDto;
import io.hhplus.tdd.chap1_2.entity.lecture.LectureDate;
import io.hhplus.tdd.chap1_2.entity.userinfo.ApplicationLog;
import io.hhplus.tdd.chap1_2.enums.ApplicationStateType;
import io.hhplus.tdd.chap1_2.repository.ApplicationLogRepository;
import io.hhplus.tdd.chap1_2.repository.LectureDateRepository;
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

    public ResponseEntity<ApplicationStateType> setApplicationLecture(ReqApplicationLectureDto req) {

        // dto 만들기
        // 특강 신청 가능 여부 확인
        List<ApplicationLog> applicationLogList = applicationLogRepository.findApplicationLogsByUserIdAndLectureId(req.getUserId(), req.getLectureId());

        if (!CollectionUtils.isEmpty(applicationLogList)) {
            // 로그 기록
            ApplicationLog applicationLog = new ApplicationLog();
            applicationLog.setLectureId(req.getLectureId());
            applicationLog.setLectureDateId(req.getLectureDateId());
            applicationLog.setUserId(req.getUserId());
            applicationLog.setState(ApplicationStateType.FAILED.name());
            applicationLog.setApplicationDate(LocalDateTime.now());
            applicationLogRepository.save(applicationLog);
            return ResponseEntity.ok(ApplicationStateType.FAILED);
        }

        // 로그 기록
        ApplicationLog applicationLog = new ApplicationLog();
        applicationLog.setLectureId(req.getLectureId());
        applicationLog.setLectureDateId(req.getLectureDateId());
        applicationLog.setUserId(req.getUserId());
        applicationLog.setState(ApplicationStateType.SUCCESS.name());
        applicationLog.setApplicationDate(LocalDateTime.now());
        applicationLogRepository.save(applicationLog);

        // 특강 수강 정보 조회
        Optional<LectureDate> lectureDateDto = lectureDateRepository.findById(req.getLectureDateId());
        if (lectureDateDto.isEmpty()) {
            return ResponseEntity.ok(ApplicationStateType.FAILED);
        }

        // 특강 신청자 카운트 업데이트
        LectureDate lectureDate = lectureDateDto.get();
        lectureDate.setApplicationUserCount(lectureDate.getApplicationUserCount() + 1);

        if (lectureDate.getApplicationUserCount() > 30) {
            return ResponseEntity.ok(ApplicationStateType.FAILED);
        } else if (lectureDate.getApplicationUserCount() == 30) {
            lectureDate.setApplicationAvailableYn(false);
        }
        lectureDateRepository.save(lectureDate);
        return ResponseEntity.ok(ApplicationStateType.SUCCESS);
    }
}
