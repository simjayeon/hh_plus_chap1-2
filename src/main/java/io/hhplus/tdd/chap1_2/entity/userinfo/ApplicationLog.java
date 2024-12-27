package io.hhplus.tdd.chap1_2.entity.userinfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class ApplicationLog {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "lecture_id")
    private Long lectureId;
    @Column(name = "lecture_date_id")
    private Long lectureDateId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "state")
    private String state;
    @Column(name = "application_date")
    private Date applicationDate;
}
