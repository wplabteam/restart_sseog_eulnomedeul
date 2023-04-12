package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "BLOOD_CHECK")
public class BloodCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodCheckId;
    private Long song;
    private Long oh;
    private Long lee;
    private Long kang;
    private LocalDateTime regDate;
}
