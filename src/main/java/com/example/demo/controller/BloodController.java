package com.example.demo.controller;

import com.example.demo.dto.BloodCheckDto;
import com.example.demo.service.BloodCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BloodController {

    private final BloodCheckService bloodCheckService;
    @RequestMapping("/test")
    public String blood(BloodCheckDto dto) {
        System.out.println("dto:" + dto.getSong());

        return "blood/blood";
    }
}
