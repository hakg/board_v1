package com.hakg.boardv1.service;

import com.hakg.boardv1.domain.Member;
import com.hakg.boardv1.mapper.MemberMapper;
import com.hakg.boardv1.web.dto.MemberJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(MemberJoinRequest request) {
        // 아이디 중복 검사
        if (memberMapper.findByUsername(request.getUsername()) != null) {
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }

        // 이메일 중복 검사
        if (memberMapper.findByEmail(request.getEmail()) != null) {
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 확인
        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        
        // 회원 저장
        Member member = request.toEntity(encodedPassword);
        memberMapper.save(member);
    }

    @Transactional
    public void updateLastLoginDate(String username) {
        Member member = memberMapper.findByUsername(username);
        if (member != null) {
            member.updateLastLoginDate();
            memberMapper.updateLastLoginDate(member);
        }
    }
}
