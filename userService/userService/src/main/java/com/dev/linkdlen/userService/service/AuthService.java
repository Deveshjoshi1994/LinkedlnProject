package com.dev.linkdlen.userService.service;

import com.dev.linkdlen.userService.dto.LoginRequestDto;
import com.dev.linkdlen.userService.dto.SignupRequestDto;
import com.dev.linkdlen.userService.dto.UserDto;
import com.dev.linkdlen.userService.entity.User;
import com.dev.linkdlen.userService.exception.BadRequestException;
import com.dev.linkdlen.userService.exception.ResourceNotFoundException;
import com.dev.linkdlen.userService.repository.UserRepository;
import com.dev.linkdlen.userService.util.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signup(SignupRequestDto signupRequestDto) {
        log.info("Signup a user with email: {}", signupRequestDto.getEmail());
        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());

        if (exists) {
            throw new BadRequestException("User already exists");
        }
        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        user = userRepository.save(user);

        return modelMapper.map(user, UserDto.class);

    }

    public String login(LoginRequestDto loginRequestDto) {
        log.info("Login request for user with email: {}", loginRequestDto.getEmail());

        User user =
                userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with email id " + loginRequestDto.getEmail()));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            throw new BadRequestException("Incorrect Password");
        }

        return jwtService.generateAccessToken(user);
    }
}
