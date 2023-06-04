package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class lyhController {


    /**
     * @ResponseBody 를 사용하지 않는 일반적인 메서드
     * 스프링 부트의 경우 웹 브라우저에서 (ex) localhost:8080/mvc 를 통해
     * 사이트에 접속하면 내장 톰캣 서버에서 mvc를 스프링에 넘겨주고,
     * 스프링에서는 컨트롤러에 있는 Mapping 을 따라 찾아간다.
     * Mapping 된다면 ViewResolver 로 토스되어(View 를 통해서) 처리된다.
     */
    @GetMapping("lyh")
    public String hiImLyh(Model model) {
        model.addAttribute("data", "이용호");
        return "lyh/lyh";
    }

    @GetMapping("lyh-mvc") //GET 방식으로 데이터를 받고 , Model 사용하여 데이터를 넘겨준다.
    public String lyhMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "lyh/lyh-template";
    }

    /**
     * @RepsonseBody 사용, 반환형이 String
     * @param name
     * @return
     */
    @GetMapping("lyh-string")
    @ResponseBody
    public String lyhString(@RequestParam("name") String name) {
        return "lyh " + name;
    }
}

