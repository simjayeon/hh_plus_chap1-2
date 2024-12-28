package io.hhplus.tdd.chap1_2.service;

import io.hhplus.tdd.chap1_2.dto.ApplicationLogDto;
import io.hhplus.tdd.chap1_2.dto.LectureDateDto;
import io.hhplus.tdd.chap1_2.dto.LectureDto;
import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import io.hhplus.tdd.chap1_2.entity.userinfo.ApplicationLog;
import io.hhplus.tdd.chap1_2.entity.userinfo.UserInfo;
import io.hhplus.tdd.chap1_2.enums.ApplicationAvailableType;
import io.hhplus.tdd.chap1_2.enums.ApplicationStateType;
import io.hhplus.tdd.chap1_2.repository.ApplicationLogRepository;
import io.hhplus.tdd.chap1_2.repository.LectureRepository;
import io.hhplus.tdd.chap1_2.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final ApplicationLogRepository applicationLogRepository;
    private final UserInfoRepository userInfoRepository;

    public ResponseEntity<List<LectureDto>> getApplicationAvailableLectureList(Long userId, String lectureDate) {

        UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);

        // 신청자 로그 조회
        List<ApplicationLog> applicationLogs = applicationLogRepository.findApplicationLogsByUserInfoAndState(userInfo, ApplicationStateType.SUCCESS.name());

        // 강의 목록 조회
        List<Lecture> lectureList = lectureRepository.findAllByLectureDate(lectureDate);
        Set<Lecture> applicationLogLectures = applicationLogs.stream()
                .map(ApplicationLog::getLecture)
                .collect(Collectors.toSet());

        // 신청자 로그의 강의 id 목록
        Set<Long> applicationLogLectureIds = applicationLogLectures.stream()
                .map(Lecture::getId)
                .collect(Collectors.toSet());

        // 강의 가능 여부 구분 및 dto 변환
        List<LectureDto> lectureDtos = new ArrayList<>();
        for (Lecture lecture : lectureList) {
            LectureDateDto lectureDateDto = LectureDateDto.of(lecture.getLectureDate());

            if (applicationLogLectureIds.contains(lecture.getId())) {
                lectureDateDto.setAvailableYn(ApplicationAvailableType.UNAVAILABLE);
            }

            List<LectureDateDto> lectureDateDtos = List.of(lectureDateDto);
            lectureDtos.add(LectureDto.of(lecture, lectureDateDtos));
        }

        return ResponseEntity.ok(lectureDtos);
    }

    public ResponseEntity<List<ApplicationLogDto>> getLectureApplicationLogList(Long userId, Long lectureId) {
        List<ApplicationLog> applicationLogs = new ArrayList<>();

        // 특정 유저의 특정 강의 log를 조회하는 경우
        if (userId != null && lectureId != null) {
            UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
            applicationLogs = applicationLogRepository.findApplicationLogsByUserInfoAndLectureId(userInfo, lectureId);
        } else if (userId != null) {
            // 특정 유저의 log를 조회하는 경우
            UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
            applicationLogs = applicationLogRepository.findApplicationLogsByUserInfo(userInfo);
        } else if (lectureId != null) {
            // 특정 강의의 log를 조회하는 경우
            applicationLogs = applicationLogRepository.findApplicationLogsByLectureId(lectureId);
        } else {
            // 모든 log를 조회하는 경우
            applicationLogs = applicationLogRepository.findAll();
        }

        List<ApplicationLogDto> applicationLogDtos = applicationLogs.stream()
                .map(ApplicationLogDto::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(applicationLogDtos);
    }
}