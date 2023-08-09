package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.study.dto.response.DiaryResponseDto;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diaries")
@DynamicUpdate
@SQLDelete(sql = "UPDATE diaries SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DiaryTag> diaryTags = new ArrayList<>();

    @Builder
    public Diary(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.deleted = false;
    }

    public DiaryResponseDto toDto() {
        return DiaryResponseDto.builder()
                .id(this.id)
                .userId(this.user.getId())
                .title(this.title)
                .content(this.content)
                .createdAt(this.createdDate.toString())
                .build();
    }
}
