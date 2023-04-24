package com.example.demo.service;


import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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


    public String login(String mbUserName, String mbPassword, HttpSession session, Model model) {
        Member member = memberRepository.findByMbUserName(mbUserName);
        if (member != null && passwordEncoder.matches(mbPassword, member.getMbPassword())) {
            session.setAttribute("user", member);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "member/login";
        }

    }

}

