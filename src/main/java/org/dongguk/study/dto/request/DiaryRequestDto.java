package org.dongguk.study.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.DiaryTagDto;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class DiaryRequestDto {
    final private String title;
    final private String content;
    final private List<DiaryTagDto> tags;

    public Diary toEntity(User user) {
        return Diary.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
