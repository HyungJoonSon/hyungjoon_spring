package org.dongguk.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.UserDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    public UserDto readUserProfile() {
        Long id = 0L;
        String name = "정구연";
        String intro = "9시까지 남아있음";

        return UserDto.builder()
                .id(id)
                .name(name)
                .introduction(intro).build();
    }
}
