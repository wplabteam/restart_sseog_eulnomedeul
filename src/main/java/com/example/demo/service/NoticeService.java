package com.example.demo.service;

import com.example.demo.dto.NoticeSaveDto;
import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    @Autowired
    private final NoticeRepository noticeRepository;




    public void saveNotice(NoticeSaveDto noticeSaveDto) {
        Notice notice = new Notice();

        notice.setNtTitle(noticeSaveDto.getNtTitle());
        notice.setNtContent(noticeSaveDto.getNtContent());
        notice.setFileSeq(noticeSaveDto.getFileSeq());
        notice.setSeq(noticeSaveDto.getSeq());
        notice.setNtIsView(noticeSaveDto.getNtIsView());
        notice.setNtRegDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }
}
