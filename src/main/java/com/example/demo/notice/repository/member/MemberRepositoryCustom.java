package com.example.demo.notice.repository.member;

import com.example.demo.dto.MemberListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<MemberListDto> memberList(Pageable pageable);
}
