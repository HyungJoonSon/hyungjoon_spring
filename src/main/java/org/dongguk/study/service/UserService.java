package org.dongguk.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.request.UserRequestDto;
import org.dongguk.study.dto.response.UserResponseDto;
import org.dongguk.study.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto readUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        return UserResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname()).build();
    }

    @Transactional
    public UserResponseDto updateUserProfile(Long userId, String name) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        user.updateNickname(name);
        return UserResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname()).build();
    }

    @Transactional
    public Boolean deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        userRepository.delete(user);
        return Boolean.TRUE;
    }
}
