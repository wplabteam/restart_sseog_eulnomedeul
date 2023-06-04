package com.example.demo.controller;

import com.example.demo.entity.Member2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class Member2Controller {

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Validated @RequestBody Member2 user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 실패 시 에러 처리
            List<ObjectError> errors = bindingResult.getAllErrors();
            // 에러 메시지 추출 및 처리
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        // 유효성 검사 통과 시 사용자 생성 로직 처리
        // ...

        return ResponseEntity.ok("사용자 생성 완료");
    }
}
