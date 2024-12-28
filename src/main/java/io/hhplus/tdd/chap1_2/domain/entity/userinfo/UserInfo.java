package io.hhplus.tdd.chap1_2.domain.entity.userinfo;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user_info")
public class UserInfo {

    @Id
    private Long id;
    private String userName;
    private String userType;

}
