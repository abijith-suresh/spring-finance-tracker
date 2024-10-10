package com.ust.tracker.repo;

import com.ust.tracker.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInfoRepo extends JpaRepository <UserInfo, UUID> {
    UserInfo findByUsername(String username);
}
