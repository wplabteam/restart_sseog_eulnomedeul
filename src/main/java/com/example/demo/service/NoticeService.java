package com.example.demo.service;

import com.example.demo.dto.NoticeSaveDto;
import com.example.demo.entity.File;
import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.util.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    @Autowired
    private final NoticeRepository noticeRepository;

    private final FileService fileService;




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

    public List<Notice> searchNoticeList() {

        return noticeRepository.findAll();
    }

//    public Notice noticeView(Long seq) {
//        Notice noticeView = noticeRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq=" + seq));
////
////        File noticeFile = fileService.findById(noticeView.getSeq());
////        if(noticeFile != null){
//////            noticeView.setFileSeq(noticeFile);
////        }
//    }
}
