package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "tb_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mb_id")
    private Long seq;
    //회원이름
    private String mbName;
    // 회원 아이디
    private String mbUserName;
    // 회원 비밀번호
    private String mbPassword;
    // 회원 전화번호
    private String mbPhone;
    // 회원 이메일
    private String mbEmail;
    // 회원 주소
    private String mbAddress;
    // 회원 상세주소
    private String mbDetailAddress;
    //PRINCIPAL_NAME
    private String  sessionUsername;
}
