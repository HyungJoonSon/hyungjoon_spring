package org.dongguk.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.DiaryDto;
import org.dongguk.study.dto.DiaryRequestDto;
import org.dongguk.study.dto.UserDto;
import org.dongguk.study.service.DiaryService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DiaryController {
    final private DiaryService diaryService;

    @PostMapping("/diary")
    public DiaryDto createDiary(@RequestBody DiaryRequestDto request) {
        return diaryService.createDiary(request);
    }

    @GetMapping("/diary/{id}")
    public DiaryDto readDiary(@PathVariable("id") Long id) {
        return diaryService.readDiary(id);
    }

    @PutMapping("/diary/{id}")
    public DiaryDto updateDiary(@PathVariable("id") Long id, @RequestBody DiaryRequestDto request) {
        return diaryService.updateDiary(1L, request);
    }

    @DeleteMapping("/diary/{id}")
    public Boolean deleteUserProfile(@PathVariable("id") Long id) {
        return diaryService.deleteDiary(id);
    }

}
