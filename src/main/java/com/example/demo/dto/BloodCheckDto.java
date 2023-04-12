package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloodCheckDto {
    private Long bloodCheckId;
    private Long song;
    private Long oh;
    private Long lee;
    private Long kang;
    private LocalDateTime regDate;
}
