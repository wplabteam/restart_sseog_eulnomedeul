package com.example.demo.repository;

import com.example.demo.dto.NoticeSaveDto;
import com.example.demo.entity.QNotice;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.Projection;

import javax.persistence.EntityManager;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    public NoticeRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public NoticeSaveDto searchNoticeView(Long seq) {
        QNotice notice = QNotice.notice;

        NoticeSaveDto content = jpaQueryFactory
                .select(Projections.constructor(
                        NoticeSaveDto.class,
                        notice.seq,
                        notice.ntTitle,
                        notice.ntContent,
                        notice.count,
                        notice.fileSeq,
                        notice.ntIsView,
                        notice.ntIsDel,
                        notice.ntRegDate
                ))
                .from(notice)
                .where(notice.seq.eq(seq))
                .fetchOne();
        return content;
    }
}
