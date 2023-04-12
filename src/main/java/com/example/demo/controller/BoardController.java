package com.example.demo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @RequestMapping("/board/pwChk")
    public String pwChk() {

        return "board/pwChk";

    }

    @PostMapping("/board/pwChk")
    public String pwChk(@RequestParam(value = "password" ,required = false) String password , @RequestParam(value="name" , required = false) String name) {
        System.out.println("name:" + name);
        System.out.println("pw:" + password);
        // 입력한 값 화면에 출력하기

        return "board/pwChk";
    }
}
