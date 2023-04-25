package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModalController {
    @RequestMapping ("common/modal")
    public ModelAndView showModal(@RequestParam("msg") String message, @RequestParam("url") String url) {
        ModelAndView modelAndView = new ModelAndView("common/modal");
        modelAndView.addObject("message", message);
        modelAndView.addObject("url", url);
        return modelAndView;
    }
}
