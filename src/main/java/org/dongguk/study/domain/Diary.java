package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diarys")
@DynamicUpdate
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible;

    @Builder
    public Diary(String title, String content) {
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.title = title;
        this.content = content;
        this.isVisible = true;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void delete() {
        this.isVisible = false;
    }
}
