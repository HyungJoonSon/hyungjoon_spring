package org.dongguk.study.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dongguk.study.service.AuthService;
import org.dongguk.study.type.ELoginProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/kakao")
    public void getKakaoRedirectURL(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(authService.getRedirectUrl(ELoginProvider.KAKAO));
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> loginUsingKakao(@RequestParam("code") String authCode) {
        return ResponseEntity.ok(authService.login(authCode, ELoginProvider.KAKAO));
    }
}
