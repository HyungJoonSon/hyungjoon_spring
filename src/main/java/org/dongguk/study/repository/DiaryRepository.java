package org.dongguk.study.repository;

import org.dongguk.study.domain.Diary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @EntityGraph(attributePaths = {"diaryTags.tag"})
    Optional<Diary> findById(Long id);
}
