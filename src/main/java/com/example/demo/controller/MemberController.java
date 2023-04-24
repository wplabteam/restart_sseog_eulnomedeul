package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * method         : join
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 회원가입 페이지
     */
    @RequestMapping("/member/join")
    public String join(@ModelAttribute("memberSaveDto") Member memberSaveDto) {
        return "/member/join";
    }

    /**
     * method         : checkId
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 아이디 중복체크
     */
    @PostMapping("/join/idChk")
    public ResponseEntity<?> checkId(@RequestBody Map<String, String> requestBody) {
        String mbId = requestBody.get("mbId");
        Long checkId = memberRepository.countByMbUserName(mbId);
        if (checkId != 0L) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        } else {
            return ResponseEntity.ok(Map.of("result", "success"));
        }
    }

    /**
     * method         : joinProc
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 회원가입 처리
     */

    @PostMapping("/join/write")
    public String joinProc(@ModelAttribute("memberSaveDto") Member memberSaveDto) {

        memberSaveDto.setMbPassword(passwordEncoder.encode(memberSaveDto.getMbPassword()));
        memberService.join(memberSaveDto);

        return "/member/login";
    }

    /**
     * method         : login
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 로그인 페이지
     */

    @RequestMapping("/member/login")
    public String login(Model model) {
        model.addAttribute("memberSaveDto", new Member());
        return "/member/login";
    }
/**
 * method         : loginProc
 * author         : 오동준
 * date           : 2023/04/24
 * description    : 세션 로그인
 */

    @PostMapping("/member/login")
    public String loginProc(@ModelAttribute("memberSaveDto") Member memberSaveDto, HttpSession session, Model model) {
        Member member = memberRepository.findByMbUserName(memberSaveDto.getMbUserName());
        if (member != null && passwordEncoder.matches(memberSaveDto.getMbPassword(), member.getMbPassword())) {
            session.setAttribute("user", member);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "/member/login";
        }
    }

/**
 * method         : logout
 * author         : 오동준
 * date           : 2023/04/24
 * description    : 로그아웃
 */

@GetMapping("/member/logout")
public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
}
}
