package org.dongguk.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.DiaryDto;
import org.dongguk.study.dto.UserDto;
import org.dongguk.study.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto readUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }

    @Transactional
    public UserDto updateUserProfile(Long userId, String name) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        user.setName(name);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }

    @Transactional
    public Boolean deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        userRepository.delete(user);
        return Boolean.TRUE;
    }
}
