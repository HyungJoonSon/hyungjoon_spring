package org.dongguk.study.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class DiaryResponseDto {
    final private Long id;
    final private Long userId;
    final private String title;
    final private String content;
    final private String createdAt;
}
