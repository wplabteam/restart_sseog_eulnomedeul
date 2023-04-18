package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "blood_check")
public class BloodCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodCheckId;
    private String userName;
    private String address;
    private String addressDetail;
    private String phoneNo;
    private String email;
    private Long bloodConcentration;
    private String isHungry;
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime regDate;

    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @Column(name = "mod_date")
    private LocalDateTime modDate;
}
