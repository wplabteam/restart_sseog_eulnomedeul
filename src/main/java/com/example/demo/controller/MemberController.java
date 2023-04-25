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
    public String login(@RequestParam(name = "returnUrl", required = false) String returnUrl,Model model) {
        model.addAttribute("memberSaveDto", new Member());
        model.addAttribute("returnUrl", returnUrl);
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

        return memberService.login(memberSaveDto.getMbUserName(), memberSaveDto.getMbPassword(), session, model);
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
