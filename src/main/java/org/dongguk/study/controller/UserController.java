package org.dongguk.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.request.UserRequestDto;
import org.dongguk.study.dto.response.UserResponseDto;
import org.dongguk.study.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserResponseDto readUserProfile(Authentication authentication) {
        return userService.readUserProfile(Long.valueOf(authentication.getName()));
    }

    @GetMapping("/{userId}")
    public UserResponseDto readUserProfile(@PathVariable Long userId) {
        return userService.readUserProfile(userId);
    }

    @PutMapping("")
    public UserResponseDto updateUserProfile(@RequestBody UserRequestDto requestDto) {
        return userService.updateUserProfile(1L, "requestDto");
    }

    @DeleteMapping("")
    public Boolean deleteUserProfile() {
        return userService.deleteUserProfile(1L);
    }
}
