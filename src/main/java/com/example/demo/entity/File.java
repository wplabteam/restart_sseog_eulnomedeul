package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_seq")
    private Long seq;
    //파일코드
    private String fileMasterCode;
    // 파일 업로드 이름
    private String fileOriginalName;
    // 파일 타입
    private String fileType;
    // 등록 seq
    private Long regSeq;
    // 등록일
    private LocalDateTime regDate;

}
