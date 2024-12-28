package io.hhplus.tdd.chap1_2.service;

import io.hhplus.tdd.chap1_2.dto.LectureDateDto;
import io.hhplus.tdd.chap1_2.dto.LectureDto;
import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import io.hhplus.tdd.chap1_2.entity.userinfo.ApplicationLog;
import io.hhplus.tdd.chap1_2.enums.ApplicationAvailableType;
import io.hhplus.tdd.chap1_2.enums.ApplicationStateType;
import io.hhplus.tdd.chap1_2.repository.ApplicationLogRepository;
import io.hhplus.tdd.chap1_2.repository.LectureRepository;
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

    public ResponseEntity<List<LectureDto>> getApplicationAvailableLectureList(Long userId, String lectureDate) {

        // 신청자 로그 조회
        List<ApplicationLog> applicationLogs = applicationLogRepository.findApplicationLogsByUserIdAndState(userId, ApplicationStateType.SUCCESS.name());

        // 강의 목록 조회
        List<Lecture> lectureList = lectureRepository.findAllByLectureDate(lectureDate);
        Set<Long> applicationLogLectureIds = applicationLogs.stream()
                .map(ApplicationLog::getLectureId)
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
}