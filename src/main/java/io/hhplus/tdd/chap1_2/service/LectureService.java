package io.hhplus.tdd.chap1_2.service;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import io.hhplus.tdd.chap1_2.entity.userinfo.ApplicationLog;
import io.hhplus.tdd.chap1_2.repository.ApplicationLogRepository;
import io.hhplus.tdd.chap1_2.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final ApplicationLogRepository applicationLogRepository;


    public ResponseEntity<List<Lecture>> getApplicationAvailableLectureList(Long userId, Long lectureDateId) {

        // 신창자 로그 조회
        List<ApplicationLog> applicationLogs = applicationLogRepository.findApplicationLogsByUserId(userId);

        List<Lecture> lectureList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(applicationLogs)) {
            return ResponseEntity.ok(lectureList);
        }

        lectureList = lectureRepository.findLectureByLectureDateId(lectureDateId);
//        for (ApplicationLog applicationLog : applicationLogs) {
//             = lectureList.stream()
//                    .filter(lecture -> !lecture.getId().equals(applicationLog.getLectureId()))
//                    .collect(Collectors.toList());
//        }
//
//        for (Lecture lecture : lectureList) {
//            lecture.setApplicationAvailable(true);
//        }
        return ResponseEntity.ok(lectureList);
    }
}
