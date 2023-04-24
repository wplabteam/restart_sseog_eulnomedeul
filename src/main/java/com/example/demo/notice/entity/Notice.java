package com.example.demo.notice.entity;

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
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nt_seq")
    private Long seq;
    @Column(name = "mb_username_fk")
    // 작성자
    private String mbUserName;
    //제목
    private String ntTitle;
    //내용
    private String ntContent;
    //조회수
    @Column(name = "nt_count", columnDefinition = "integer default 0")
    private int count;
    //파일번호
    private Long fileSeq;
    //노출여부
    private String ntIsView;
    //삭제여부
    private String ntIsDel;
    // 등록일
    private LocalDateTime ntRegDate;


}
