package com.dev.linkdlen.userService.controller;

import com.dev.linkdlen.userService.dto.LoginRequestDto;
import com.dev.linkdlen.userService.dto.SignupRequestDto;
import com.dev.linkdlen.userService.dto.UserDto;
import com.dev.linkdlen.userService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        UserDto userDto = authService.signup(signupRequestDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto);

        return ResponseEntity.ok(token);

    }


}

