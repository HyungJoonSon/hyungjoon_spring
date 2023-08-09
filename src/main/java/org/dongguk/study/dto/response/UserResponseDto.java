package org.dongguk.study.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserResponseDto {
    final private Long id;
    final private String nickname;
}
