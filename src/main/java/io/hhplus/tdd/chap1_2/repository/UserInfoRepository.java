package io.hhplus.tdd.chap1_2.repository;

import io.hhplus.tdd.chap1_2.entity.userinfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
