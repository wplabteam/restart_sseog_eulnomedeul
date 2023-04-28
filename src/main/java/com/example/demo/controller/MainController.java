package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String main(HttpServletRequest request, Model model) {
        //getSession(false) 메소드를 사용하면 세션이 없을 경우에도 새로운 세션을 생성하지 않습니다.
        HttpSession session = request.getSession(false);
        String username = null;
        // 세션이 존재하면 username을 가져온다.
        if (session != null) {
            username = (String) session.getAttribute("user");
            // username이 존재하면 user에 username을 담아준다.
            if (username != null) {
                Member member = memberRepository.findByMbUserName(username);
                model.addAttribute("user", member);
            } else {
                // 사용자 정보가 존재하지 않은 경우
                session.invalidate(); // 세션 무효화
                return "redirect:member/login";
            }
        } else {
            model.addAttribute("user", null);
        }
        return "/main/main";
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
