package com.example.demo.service;

import com.example.demo.dto.BloodCheckDto;
import com.example.demo.entity.BloodCheck;
import com.example.demo.repository.BloodCheckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BloodCheckService {

    @Autowired
    private BloodCheckRepository bloodCheckRepository;
    public void insertBlood(BloodCheckDto dto) {

        BloodCheck bloodCHeck = BloodCheck.builder()
                .song(dto.getSong())
                .oh(dto.getOh())
                .lee(dto.getLee())
                .kang(dto.getKang())
                .regDate(LocalDateTime.now())
                .build();

        bloodCheckRepository.save(bloodCHeck);
    }

    public List<BloodCheckDto> selectBloodList() {

        return bloodCheckRepository.selectBloodList();
    }
}
