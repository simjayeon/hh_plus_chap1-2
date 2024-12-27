package io.hhplus.tdd.chap1_2.controller;

import io.hhplus.tdd.chap1_2.dto.LectureDto;
import io.hhplus.tdd.chap1_2.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    /**
     * TODO - 특정 유저가 날짜별로 신청 가능한 강의 목록을 조회하는 기능
     */
    @GetMapping("")
    public ResponseEntity<List<LectureDto>> getApplicationAvailableLectureList(@RequestParam Long userId,
                                                                               @RequestParam String lectureDate) {
        return lectureService.getApplicationAvailableLectureList(userId, lectureDate);
    }

}
