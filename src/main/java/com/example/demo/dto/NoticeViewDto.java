package com.example.demo.dto;


import com.example.demo.entity.File;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeViewDto {
    private Long seq;
    private String ntTitle;
    private String ntContent;
    private int count;
    private Long fileSeq;
    private String ntIsView;
    private String ntIsDel;
    private LocalDateTime ntRegDate;
    private File file;

    public NoticeViewDto(Long seq, String ntTitle, String ntContent, int count, Long fileSeq, String ntIsView, String ntIsDel, LocalDateTime ntRegDate) {
        this.seq = seq;
        this.ntTitle = ntTitle;
        this.ntContent = ntContent;
        this.count = count;
        this.fileSeq = fileSeq;
        this.ntIsView = ntIsView;
        this.ntIsDel = ntIsDel;
        this.ntRegDate = ntRegDate;
    }
}
