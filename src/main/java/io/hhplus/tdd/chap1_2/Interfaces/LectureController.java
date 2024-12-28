package io.hhplus.tdd.chap1_2.Interfaces;

import io.hhplus.tdd.chap1_2.Interfaces.dto.ApplicationLogDto;
import io.hhplus.tdd.chap1_2.Interfaces.dto.LectureDto;
import io.hhplus.tdd.chap1_2.Interfaces.dto.ReqApplicationLectureDto;
import io.hhplus.tdd.chap1_2.enums.ApplicationStateType;
import io.hhplus.tdd.chap1_2.domain.service.ApplicationService;
import io.hhplus.tdd.chap1_2.domain.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;
    private final ApplicationService applicationService;

    /**
     * TODO - 특정 유저가 날짜별로 신청 가능한 강의 목록을 조회하는 기능
     */
    @GetMapping("")
    public ResponseEntity<List<LectureDto>> getApplicationAvailableLectureList(@RequestParam Long userId,
                                                                               @RequestParam String lectureDate) {
        return lectureService.getApplicationAvailableLectureList(userId, lectureDate);
    }

    /**
     * TODO - 특강 신청
     */
    @PostMapping("/application")
    public ResponseEntity<ApplicationStateType> setApplicationLecture(@RequestBody ReqApplicationLectureDto req) {
        return applicationService.setApplicationLecture(req);
    }

    /**
     * TODO - 특강 신청 로그 조회
     */
    @GetMapping("/application/log")
    public ResponseEntity<List<ApplicationLogDto>> getApplicationAvailableLectureList(@RequestParam(required = false) Long userId,
                                                                                      @RequestParam(required = false) Long lectureId) {
        return lectureService.getLectureApplicationLogList(userId, lectureId);
    }


}
