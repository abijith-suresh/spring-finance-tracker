package com.ust.tracker.controller;

import com.ust.tracker.dto.UserInfoDto;
import com.ust.tracker.exception.UserNotFoundException;
import com.ust.tracker.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public ResponseEntity<UserInfoDto> addUser(@RequestBody @Valid UserInfoDto userInfoDto){
        UserInfoDto savedUser = userInfoService.createUser(userInfoDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserInfoDto> getUser(@PathVariable String username){
        try{
            UserInfoDto userInfoDto = userInfoService.getByUsername(username);
            return ResponseEntity.ok(userInfoDto);
        } catch (UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
