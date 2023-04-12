package com.example.demo.repository;

import com.example.demo.dto.BloodCheckDto;

import java.util.List;

public interface BloodCheckRepositoryCustom {
    List<BloodCheckDto> selectBloodList();
}
