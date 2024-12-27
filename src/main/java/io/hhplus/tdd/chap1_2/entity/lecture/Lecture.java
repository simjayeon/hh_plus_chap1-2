package io.hhplus.tdd.chap1_2.entity.lecture;

import io.hhplus.tdd.chap1_2.entity.userinfo.UserInfo;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "lecture_title")
    private String lectureTitle;
//    @Column(name = "lecturer_id")
//    private Long lecturerId;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", insertable = false, updatable = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LectureDate> lectureDate = new ArrayList<>();

}
