package com.example.demo.notice.repository.member;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor , MemberRepositoryCustom{

    Long countByMbUserName(String mbId);

    Member findByMbUserName(String mbUserName);

}
