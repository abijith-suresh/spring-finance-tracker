package com.ust.tracker.service;

import com.ust.tracker.dto.UserInfoDto;
import com.ust.tracker.exception.UserNotFoundException;
import com.ust.tracker.model.UserInfo;
import com.ust.tracker.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserInfoDto mapToDto(UserInfo userInfo){
        return new UserInfoDto(
                userInfo.getUsername(),
                userInfo.getEmail(),
                null,
                userInfo.getFullName(),
                userInfo.getPhoneNumber()
        );
    }

    public UserInfoDto createUser(UserInfoDto userInfoDto){
        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(userInfoDto.getUsername());
        userInfo.setEmail(userInfoDto.getEmail());
        userInfo.setFullName(userInfoDto.getFullName());
        userInfo.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        userInfo.setPhoneNumber(userInfoDto.getPhoneNumber());

        UserInfo savedUser = userInfoRepo.save(userInfo);

        return mapToDto(savedUser);
    }

    public UserInfoDto getByUsername(String username) throws UserNotFoundException {
        UserInfo userInfo = userInfoRepo.findByUsername(username);
        if (userInfo == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        return mapToDto(userInfo);
    }
}
