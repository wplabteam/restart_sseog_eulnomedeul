package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeSaveDto {
    private Long seq;
    private String ntTitle;
    private String ntContent;
    private int count;
    private Long fileSeq;
    private String ntIsView;
    private String ntIsDel;
}
