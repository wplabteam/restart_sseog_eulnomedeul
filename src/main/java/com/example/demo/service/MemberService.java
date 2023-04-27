package com.example.demo.service;


import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void join(Member memberSaveDto) {
        // 회원 정보 db 저장
        memberRepository.save(memberSaveDto);
    }

    /**
     * method         : login
     * author         : 오동준
     * date           : 2023/04/24
     * description    : 로그인처리
     */
    public Member login(String mbUserName, String mbPassword) {

        // 1. 아이디가 있는지 확인
        Member member = memberRepository.findByMbUserName(mbUserName);
        // 2. 아이디가 있으면 비밀번호 확인
        if (member != null) {
            // 3. 비밀번호가 맞으면 member 리턴 아니면 null
            if (passwordEncoder.matches(mbPassword, member.getMbPassword())) {
                return member;
            }
        }
        return null;
    }
}

