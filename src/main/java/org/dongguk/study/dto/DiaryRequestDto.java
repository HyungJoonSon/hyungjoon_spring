package org.dongguk.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiaryRequestDto {
    private String title;
    private String content;

    @Builder
    public DiaryRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
