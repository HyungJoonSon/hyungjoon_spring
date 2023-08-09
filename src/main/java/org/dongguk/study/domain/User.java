package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dongguk.study.dto.request.UserRequestDto;
import org.dongguk.study.dto.response.UserResponseDto;
import org.dongguk.study.type.ELoginProvider;
import org.dongguk.study.type.EUserRole;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoginProvider provider;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private EUserRole role;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Diary> diaries = new ArrayList<>();

    @Builder
    public User(String socialId, String password, ELoginProvider provider, EUserRole role, String nickname) {
        this.socialId = socialId;
        this.password = password;
        this.provider = provider;
        this.role = role;
        this.nickname = nickname;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.refreshToken = null;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserResponseDto toDto() {
        return UserResponseDto.builder()
                .id(this.id)
                .nickname(this.nickname)
                .build();
    }
}