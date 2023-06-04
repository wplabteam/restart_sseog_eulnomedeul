package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Member2SaveDto {

    private Long mem_id;
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @Min(value=18, message = "나이는 18세 이상이여야 합니다.")
    private int age;
}
