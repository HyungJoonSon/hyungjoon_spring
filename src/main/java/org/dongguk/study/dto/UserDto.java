package org.dongguk.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String introduction;

    @Builder
    public UserDto(Long id, String name, String introduction) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
    }
}
