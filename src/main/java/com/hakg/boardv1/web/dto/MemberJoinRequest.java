package com.hakg.boardv1.web.dto;

import com.hakg.boardv1.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequest {
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String name;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .name(name)
                .build();
    }
}
