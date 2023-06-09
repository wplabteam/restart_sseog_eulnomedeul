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
        BloodCheck bloodCheck = BloodCheck.builder()
                .userName(dto.getUserName())
                .address(dto.getAddress())
                .addressDetail(dto.getAddressDetail())
                .email(dto.getEmail())
                .bloodConcentration(dto.getBloodConcentration())
                .isHungry(dto.getIsHungry())
                .phoneNo(dto.getPhoneNo())
                .build();

        bloodCheckRepository.save(bloodCheck);

    }

    public List<BloodCheckDto> selectBloodList() {

        return bloodCheckRepository.selectBloodList();
    }

    public void deleteBlood(Long bloodCheckId) {

        bloodCheckRepository.deleteById(bloodCheckId);
    }
}

// 바꿔야 되는 부분 -> 현재 사용자 인원이 정해져 있음, 나중에 회원 가입 , 로그인 생기면 회원들이 늘어나기때문에 데이터가 행으로 들어가야 한다. 이런 부분 데이터 베이스 수정 필요
