package io.hhplus.tdd.chap1_2.entity.userinfo;

import io.hhplus.tdd.chap1_2.entity.lecture.Lecture;
import io.hhplus.tdd.chap1_2.entity.lecture.LectureDate;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "application_log")
public class ApplicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
//    @Column(name = "lecture_id")
//    private Long lectureId;
//    @Column(name = "lecture_date_id")
//    private Long lectureDateId;
//    @Column(name = "user_id")
//    private Long userId;
    @Column(name = "state")
    private String state;
    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", insertable = false, updatable = false)
    private Lecture lecture;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_date_id", insertable = false, updatable = false)
    private LectureDate lectureDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserInfo userInfo;

    public ApplicationLog() {
    }

}
