package com.example.demo.controller;

import com.example.demo.common.JSONResponse;
import com.example.demo.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @RestController란 ? : @Controller는 주로 View를 반환하기 위해 사용되는 어노테이션이고 @RestController는 주로 JSON 형태로 객체 데이터를 반환하기 위해 사용되는 어노테이션이다.
 * @ResponseBody : 리턴값을 JSON 형태로 반환
 * @RequiredArgsConstructor : final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줌
 * */
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

        // 인증번호 발송
        Map result = smsService.send(mbPhone, "본인확인을 위해 인증번호<br>[" + ranCertNo + "]를 입력해주세요.");

        // 인증번호 발송 성공 시
        if (result != null) {
            // 세션에 인증번호 저장
            request.getSession().setAttribute(mbPhone, ranCertNo);
            return new JSONResponse<>(200, "SUCCESS", null);
        } else {
            // 인증번호 발송 실패 시
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
        // 인증번호 확인
        String mbPhone = requestBody.get("mbPhone");
        String certNo = requestBody.get("certNo");

        // 세션에서 인증번호 가져오기
        String sessionCertNo = (String) request.getSession().getAttribute(mbPhone);

        if (sessionCertNo == null) { // 세션에 인증번호가 없을 경우
            return new JSONResponse<>(500, "FAIL", null);
        }
        if (sessionCertNo.equals(certNo)) { // 인증번호가 일치할 경우
            return new JSONResponse<>(200, "SUCCESS", null);
        }

        // 인증번호가 일치하지 않을 경우
        return new JSONResponse<>(400, "FAIL", null);
    }

}
