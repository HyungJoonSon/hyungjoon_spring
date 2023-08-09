package org.dongguk.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dongguk.study.domain.User;
import org.dongguk.study.repository.UserRepository;
import org.dongguk.study.security.JwtProvider;
import org.dongguk.study.type.ELoginProvider;
import org.dongguk.study.type.EUserRole;
import org.dongguk.study.util.Oauth2Util;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Oauth2Util oauth2Util;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public String getRedirectUrl(ELoginProvider provider) {
        String url = "";
        switch (provider) {
            case KAKAO -> {
                url = oauth2Util.getKakaoRedirectUrl();
            }
            case GOOGLE -> {
            }
            case APPLE -> {
            }
            default -> {
                assert true;
            }
        }

        return url;
    }

    @Transactional
    public Map<?, ?> login(String authCode, ELoginProvider provider) {
        Map<String, String> map = new HashMap<>();

        String accessToken = "";
        Map<?, ?> userInfo = new HashMap<>();

        switch (provider) {
            case KAKAO -> {
                accessToken = oauth2Util.getKakaoAccessToken(authCode);
                userInfo = oauth2Util.getKakaoSocialId(accessToken);
            }
            case GOOGLE -> {
            }
            case APPLE -> {
            }
            default -> {
                assert false;
            }
        }

        if (userInfo.size() != 2) {
            throw new RuntimeException("잘못된 소셜 로그인입니다.");
        }

        Optional<User> loginUser = userRepository.findBySocialIdAndProvider(String.valueOf(userInfo.get("id")), ELoginProvider.KAKAO);

        /* 숫자 12자리 무작위 생성 */


        User user = null;
        if (loginUser.isEmpty()) {
            user = userRepository.save(User.builder()
                    .socialId((String) userInfo.get("id"))
                    .password("dddddddddddddddddddddddddd") // 무작위 생성
                    .nickname((String) userInfo.get("name"))
                    .role(EUserRole.USER)
                    .provider(ELoginProvider.KAKAO).build());
        } else {
            user = loginUser.get();
        }

        map = jwtProvider.createTotalToken(user.getId(), user.getRole());

        // refreshToken 저장 로직 필요
        user.updateRefreshToken(map.get("refresh_token"));

        return map;
    }
}
