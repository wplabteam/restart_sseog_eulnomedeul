package com.example.demo.notice.dto;

import com.example.demo.entity.File;
import com.querydsl.core.annotations.QueryProjection;
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
    private LocalDateTime ntRegDate;
//    private File file;

    public NoticeSaveDto(Long seq, String ntTitle, String ntContent, int count, Long fileSeq, String ntIsView, String ntIsDel, LocalDateTime ntRegDate) {
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
