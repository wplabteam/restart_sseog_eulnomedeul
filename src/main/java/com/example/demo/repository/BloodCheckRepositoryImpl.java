package com.example.demo.repository;

import com.example.demo.dto.BloodCheckDto;
import com.example.demo.dto.QBloodCheckDto;
import com.example.demo.entity.BloodCheck;
import com.example.demo.entity.QBloodCheck;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class BloodCheckRepositoryImpl implements BloodCheckRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public BloodCheckRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BloodCheckDto> selectBloodList() {
        QBloodCheck bloodCheck = QBloodCheck.bloodCheck;

        List<BloodCheckDto> content = jpaQueryFactory
                .select(new QBloodCheckDto(
                        bloodCheck.bloodCheckId,
                        bloodCheck.userName,
                        bloodCheck.address,
                        bloodCheck.addressDetail,
                        bloodCheck.phoneNo,
                        bloodCheck.email,
                        bloodCheck.bloodConcentration,
                        bloodCheck.isHungry,
                        bloodCheck.regDate,
                        bloodCheck.modDate
                ))
                .from(bloodCheck)
                .orderBy(bloodCheck.regDate.desc())
                .fetch();

        return content;
    }
}

