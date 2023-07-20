package org.dongguk.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class DiaryDto {
    private Long id;
    private Timestamp created_date;
    private String title;
    private String content;

    @Builder
    public DiaryDto(Long id, Timestamp created_date, String title, String content) {
        this.id = id;
        this.created_date = created_date;
        this.title = title;
        this.content = content;
    }
}
