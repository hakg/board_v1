package com.hakg.boardv1.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication) {
        // 이미 인증된 사용자인 경우 게시판 목록으로 리다이렉트
        if (authentication != null && authentication.isAuthenticated() 
            && !authentication.getName().equals("anonymousUser")) {
            return "redirect:/board/list";
        }
        return "login";
    }
}