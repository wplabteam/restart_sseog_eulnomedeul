package com.example.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberListDto {
    private String mbName;
    private String mbUserName;


    @QueryProjection
    public MemberListDto(String mbName, String mbUserName) {
        this.mbName = mbName;
        this.mbUserName = mbUserName;
    }
}
