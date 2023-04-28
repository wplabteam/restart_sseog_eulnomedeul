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


import javax.servlet.http.HttpServletRequest;
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
        // mbId를 받아서 중복체크
        String mbId = requestBody.get("mbId");
        Long checkId = memberRepository.countByMbUserName(mbId);

        // checkId 가 0이 아니면 중복
        if (checkId != 0L) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        } else {
            // checkId 가 0이면 중복아님
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

        // 비밀번호 암호화 처리
        memberSaveDto.setMbPassword(passwordEncoder.encode(memberSaveDto.getMbPassword()));
        // 회원가입 처리
        memberService.join(memberSaveDto);

        return "member/login";
    }

    /**
     * method         : login
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 로그인 페이지
     */

    @RequestMapping("/member/login")
    public String login(Model model) {
        Member member = new Member();
        model.addAttribute("memberSaveDto", member);
        return "member/login";
    }

    /**
     * method         : loginProc
     * author         : 오동준
     * date           : 2023/04/24
     * description    : 세션 로그인
     */

//    @PostMapping("/member/login")
//    public String loginProc(@ModelAttribute("memberSaveDto") Member memberSaveDto, HttpSession session, Model model) {
//
//        // 로그인 정보 조회 (아이디, 비밀번호)
//        Member member = memberService.login(memberSaveDto.getMbUserName(), memberSaveDto.getMbPassword());
//
//        // 로그인 성공
//        if (member != null) {
//            session.setAttribute("user", member);
//            return "redirect:/";
//        } else {
//            // 로그인 실패시 메시지 전달
//            model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "member/login";
//        }
//
//    }

    @PostMapping("/member/login")
    public String loginProc(@ModelAttribute("memberSaveDto") Member memberSaveDto,  HttpServletRequest request, Model model) {

        String userName = memberSaveDto.getMbUserName();
        String password = memberSaveDto.getMbPassword();

        Member member = memberService.login(userName, password);

        // 로그인 성공
        if (member != null) {
            // HttpSession 인터페이스를 이용해서  HttpServletReqeust 세션 객체를 가져온 다음 해당 세션 객체에 사용자 정보를 저장함
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", userName);
            return "redirect:/";
        } else {
            // 로그인 실패시 메시지 전달
            model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "member/login";
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
