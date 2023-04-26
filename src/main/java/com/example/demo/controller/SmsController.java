package com.example.demo.controller;

import com.example.demo.common.JSONResponse;
import com.example.demo.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    /**
     * method         : getPhoneCerNo
     * author         : 오동준
     * date           : 2023/04/26
     * description    : 인증번호 발송
     */
    @GetMapping("/join/cert")
    public JSONResponse<?> getPhoneCerNo(@RequestParam("mbPhone") String mbPhone, HttpServletRequest request) {
        String ranCertNo = smsService.getCertRandomNo(4, 2);

        Map result = smsService.send(mbPhone, "제껴지게 하지말고 본인확인을 위해 인증번호<br>[" + ranCertNo + "]를 입력해주세요.");
        if (result != null) {
            request.getSession().setAttribute(mbPhone, ranCertNo);
            return new JSONResponse<>(200, "SUCCESS", null);
        } else {
            return new JSONResponse<>(400, "FAIL", null);
        }

    }

    /**
     * method         : setPhoneCertNo
     * author         : 오동준
     * date           : 2023/04/26
     * description    : 인증번호 확인
     */
    @PostMapping("/join/cert")
    @ResponseBody
    public JSONResponse<?> setPhoneCertNo(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String mbPhone = requestBody.get("mbPhone");
        String certNo = requestBody.get("certNo");

        String sessionCertNo = (String) request.getSession().getAttribute(mbPhone);

        if (sessionCertNo == null) {
            return new JSONResponse<>(500, "FAIL", null);
        }
        if (sessionCertNo.equals(certNo)) {
            return new JSONResponse<>(200, "SUCCESS", null);
        }

        return new JSONResponse<>(400, "FAIL", null);
    }

}
