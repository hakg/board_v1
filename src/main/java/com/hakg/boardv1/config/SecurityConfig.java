package com.hakg.boardv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // 생성자 주입
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }

    // 인증 성공 핸들러 (세션 유지 및 리다이렉트)
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // 세션 유지
            request.getSession().setMaxInactiveInterval(30 * 60); // 30분 세션 유지

            // 이전에 요청한 페이지로 리다이렉트 (없으면 기본 페이지로)
            RequestCache requestCache = requestCache();
            String redirectUrl = requestCache.getRequest(request, response).getRedirectUrl();
            
            if (redirectUrl != null && !redirectUrl.contains("/login")) {
                response.sendRedirect(redirectUrl);
            } else {
                response.sendRedirect("/board/list");
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .requestCache(cache -> cache
                .requestCache(requestCache())
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/members/join", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler()) // 커스텀 성공 핸들러 사용
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                .deleteCookies("JSESSIONID") // 세션 쿠키 삭제
                .permitAll()
            )
            .userDetailsService(userDetailsService);
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}