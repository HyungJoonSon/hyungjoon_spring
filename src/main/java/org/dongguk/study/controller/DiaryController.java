package org.dongguk.study.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.study.service.DiaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class DiaryController {
    final private DiaryService diaryService;
}
