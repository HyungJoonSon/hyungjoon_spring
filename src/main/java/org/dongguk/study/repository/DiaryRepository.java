package org.dongguk.study.repository;

import org.dongguk.study.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByIdAndIsVisible(Long id, Boolean isVisible);

    @Query(value = "select d from Diary d where d.id=:id and d.isVisible = true")
    Optional<Diary> getByIdAndIsVisible(@Param("id") Long id);
}
