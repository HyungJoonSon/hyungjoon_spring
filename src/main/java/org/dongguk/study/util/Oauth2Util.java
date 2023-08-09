package org.dongguk.study.util;


import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class Oauth2Util {
    // KAKAO 용 Data
    @Value("${client.provider.kakao.authorization-uri}")
    private String KAKAO_AUTHORIZATION_URL;
    @Value("${client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URL;
    @Value("${client.provider.kakao.user-info-uri}")
    private String KAKAO_USERINFO_URL;
    @Value("${client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;
    @Value("${client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getKakaoRedirectUrl() {
        return KAKAO_AUTHORIZATION_URL
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public String getKakaoAccessToken(String authCode) {
        /**
         * 헤더를 생성함
         * KAKAO에서 요구하는 형식으로 태그를 달아줌
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("client_secret", KAKAO_CLIENT_SECRET);
        params.add("redirect_uri", KAKAO_REDIRECT_URL);
        params.add("code", authCode);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return JsonParser.parseString(Objects.requireNonNull(response.getBody()))
                .getAsJsonObject().get("access_token").getAsString();
    }

    public Map<? extends Object ,? extends Object> getKakaoSocialId(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer "+ accessToken);
        httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String >> kakaoProfileRequest= new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USERINFO_URL,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        // 토큰을 사용하여 사용자 정보 추출
        JsonElement element = JsonParser.parseString(Objects.requireNonNull(response.getBody()));

        Map<String, String> map = new HashMap<>();
        map.put("name", element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString());
        map.put("id", element.getAsJsonObject().get("id").getAsString());

        return map;
    }
}
