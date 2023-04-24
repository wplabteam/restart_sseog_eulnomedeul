package com.example.demo.controller;

import com.example.demo.entity.Member;
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
    @RequestMapping("/")
    public String main(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user.getMbName());
        } else {
            model.addAttribute("user", null);
        }

        return "/main/main";
    }

    @RequestMapping("/kakaoMap")
    public String kakaoMap() {
        return "kakao/kakaoMap";
    }

}
