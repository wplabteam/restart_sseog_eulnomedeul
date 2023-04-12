package com.example.demo.controller;

import com.example.demo.common.JSONResponse;
import com.example.demo.dto.BloodCheckDto;
import com.example.demo.service.BloodCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BloodController {

    private final BloodCheckService bloodCheckService;
    @RequestMapping("/blood/write")
    public String blood(BloodCheckDto dto) {
        System.out.println("dto:" + dto.getSong());

        return "blood/blood";
    }

    @PostMapping("/blood/save")
    @ResponseBody
    public JSONResponse<?> saveBlood(BloodCheckDto dto) {
        try {
            bloodCheckService.insertBlood(dto);
        }catch (Exception e) {
            log.error("등록 에러 : ", e);
        }

        return new JSONResponse<>(200, "SUCCESS", null);
    }

    @GetMapping("/blood/list")
    public String bloodList(Model model) {

        try {
            List<BloodCheckDto> list = bloodCheckService.selectBloodList();
            model.addAttribute("list",list);
        } catch (Exception e) {
            log.error("리스트 에러 :",e);
        }
        return "blood/blood_list";
    }
}
