package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.notice.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MemberRepository memberRepository;

    /**
     * method         : main
     * author         : 오동준
     * date           : 2023/04/28
     * description    : 메인 페이지 스프링세션 사용
     */
    @RequestMapping("/")
    public String main(HttpSession session, Model model) {

        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            model.addAttribute("user", member);
            return "main/main";
        } else {
            session.invalidate(); // 세션 무효화
            return "redirect:member/login";
        }
    }







//    /**
//     * method         : main
//     * author         : 오동준
//     * date           : 2023/04/21
//     * description    : 메인 페이지 세션로그인
//     */
//
//    @RequestMapping("/")
//    public String main(HttpSession session, Model model) {
//        Member user = (Member) session.getAttribute("user");
//        if (user != null) {
//            model.addAttribute("user", user.getMbName());
//        } else {
//            model.addAttribute("user", null);
//        }
//
//        return "/main/main";
//    }



    @RequestMapping("/kakaoMap")
    public String kakaoMap() {
        return "kakao/kakaoMap";
    }

}
