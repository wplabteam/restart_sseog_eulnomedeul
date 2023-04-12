package com.example.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection

    public BloodCheckDto(Long bloodCheckId, Long song, Long oh, Long lee, Long kang, LocalDateTime regDate) {
        this.bloodCheckId = bloodCheckId;
        this.song = song;
        this.oh = oh;
        this.lee = lee;
        this.kang = kang;
        this.regDate = regDate;
    }
}
