package com.example.demo.notice.repository.member;

import com.example.demo.dto.MemberListDto;
import com.example.demo.dto.QMemberListDto;
import com.example.demo.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<MemberListDto> memberList(Pageable pageable) {
        QMember member = QMember.member;

        List<MemberListDto> content = jpaQueryFactory
                .select(new QMemberListDto(
                        member.mbName,
                        member.mbUserName
                ))
                .from(member)
                .orderBy(member.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = jpaQueryFactory
                .select(member.count())
                .from(member)
                .fetch().get(0);
        return new PageImpl<>(content, pageable, total);
    }
}
