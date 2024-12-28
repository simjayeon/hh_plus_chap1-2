package io.hhplus.tdd.chap1_2.domain.entity.lecture;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lecture_date")
public class LectureDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "lecture_id")
    private Long lectureId;
    @Column(name = "lecture_date")
    private LocalDateTime lectureDate;
    @Column(name = "deadline_date")
    private LocalDateTime deadlineDate;
    @Column(name = "application_user_count")
    private Integer applicationUserCount;
    @Column(name = "application_available_yn")
    private Boolean applicationAvailableYn;

}