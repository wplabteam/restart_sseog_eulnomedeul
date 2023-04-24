package com.example.demo.notice.repository;

import com.example.demo.dto.NoticeViewDto;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.entity.QNotice;
import com.example.demo.notice.repository.NoticeRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    public NoticeRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public NoticeViewDto searchNoticeView(Long seq) {
        QNotice notice = QNotice.notice;

        NoticeViewDto content = jpaQueryFactory
                .select(Projections.constructor(
                        NoticeViewDto.class,
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

    public List<Notice> searchNoticeList() {

        QNotice notice = QNotice.notice;

        List<Notice> content = jpaQueryFactory
                .select(Projections.constructor(
                        Notice.class,
                        notice.seq,
                        notice.mbUserName,
                        notice.ntTitle,
                        notice.ntContent,
                        notice.count,
                        notice.fileSeq,
                        notice.ntIsView,
                        notice.ntIsDel,
                        notice.ntRegDate
                ))
                .from(notice)
                .where(notice.ntIsDel.eq("N"))
                .orderBy(notice.seq.desc())
                .fetch();
        return content;
    }
}
