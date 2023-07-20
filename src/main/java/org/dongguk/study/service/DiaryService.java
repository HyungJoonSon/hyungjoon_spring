package org.dongguk.study.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.DiaryDto;
import org.dongguk.study.dto.DiaryRequestDto;
import org.dongguk.study.dto.UserDto;
import org.dongguk.study.repository.DiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;


    public DiaryDto createDiary(DiaryRequestDto request) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        Diary diary = diaryRepository.save(Diary.builder()
                .title(request.getTitle())
                .content(request.getContent()).build());

        return DiaryDto.builder()
                .id(diary.getId())
                .created_date(diary.getCreatedDate())
                .title(diary.getTitle())
                .content(diary.getContent()).build();
    }


    public DiaryDto readDiary(Long diaryId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        Diary diary = diaryRepository.findByIdAndIsVisible(diaryId, true).orElseThrow(() -> new RuntimeException("일기 없어용"));

        return DiaryDto.builder()
                .id(diary.getId())
                .created_date(diary.getCreatedDate())
                .title(diary.getTitle())
                .content(diary.getContent()).build();
    }

    @Transactional
    public DiaryDto updateDiary(Long diaryId, DiaryRequestDto request) {
        Diary diary = diaryRepository.findByIdAndIsVisible(diaryId, true).orElseThrow(() -> new RuntimeException("일기 없어용"));

        diary.update(request.getTitle(), request.getContent());

        return DiaryDto.builder()
                .id(diary.getId())
                .created_date(diary.getCreatedDate())
                .title(diary.getTitle())
                .content(diary.getContent()).build();
    }

    @Transactional
    public Boolean deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findByIdAndIsVisible(diaryId, true).orElseThrow(() -> new RuntimeException("일기 없어용"));

        diary.delete();

        return Boolean.TRUE;
    }
}
