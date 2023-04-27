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
        memberRepository.save(memberSaveDto);
    }

    /**
     * method         : login
     * author         : 오동준
     * date           : 2023/04/24
     * description    : 로그인처리
     */
    public Member login(String mbUserName, String mbPassword) {

        Member member = memberRepository.findByMbUserName(mbUserName);
        if (member != null) {
            if (passwordEncoder.matches(mbPassword, member.getMbPassword())) {
                return member;
            }
        }
        return null;
    }
}

