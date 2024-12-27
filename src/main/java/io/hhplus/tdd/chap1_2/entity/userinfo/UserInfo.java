package io.hhplus.tdd.chap1_2.entity.userinfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
