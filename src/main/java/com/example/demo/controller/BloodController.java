package com.example.demo.controller;

import com.example.demo.common.JSONResponse;
import com.example.demo.dto.BloodCheckDto;
import com.example.demo.service.BloodCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BloodController {
    /**
    *
    * BloodController의 설명을 여기에 작성한다
     * 혈당 체크 리스트 / 등록 부분
    * @author SONG_DA_WOON
    * @version 1.0.0
    * 작성일 2023/04/17
    **/

    private final BloodCheckService bloodCheckService;

    @GetMapping("/blood/write")
    public String bloodWrite() {
        return "blood/blood_write";
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

    @PostMapping("/blood/delete")
    @ResponseBody
    public JSONResponse<?> deleteBlood(@RequestBody Map<String, Object> request) {
        try {
            Long bloodCheckId = Long.valueOf(request.get("id").toString());
            bloodCheckService.deleteBlood(bloodCheckId);
        } catch (Exception e) {
            log.error("삭제 오류 : ", e);
        }

        return new JSONResponse<>(200, "SUCCESS", null);
    }
}
