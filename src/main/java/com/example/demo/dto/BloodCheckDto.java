package com.example.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloodCheckDto {
    private Long bloodCheckId;
    private String userName;
    private String address;
    private String addressDetail;
    private String phoneNo;
    private String email;
    private Long bloodConcentration;
    private String isHungry;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @QueryProjection

    public BloodCheckDto(Long bloodCheckId, String userName, String address, String addressDetail, String phoneNo, String email, Long bloodConcentration, String isHungry, LocalDateTime regDate, LocalDateTime modDate) {
        this.bloodCheckId = bloodCheckId;
        this.userName = userName;
        this.address = address;
        this.addressDetail = addressDetail;
        this.phoneNo = phoneNo;
        this.email = email;
        this.bloodConcentration = bloodConcentration;
        this.isHungry = isHungry;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
