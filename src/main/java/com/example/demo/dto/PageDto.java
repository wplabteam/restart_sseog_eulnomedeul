package com.example.demo.dto;

import lombok.Data;

@Data
public class PageDto {
    private Long offset;  // 시작 번호
    private Integer size;  // 페이지 사이즈
    private Integer page;  // 페이지 번호
    private Integer rowPerPage;  // 페이지당 행 개수
}
