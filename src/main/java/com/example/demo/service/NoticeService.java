package com.example.demo.service;

import com.example.demo.notice.dto.NoticeSaveDto;
import com.example.demo.dto.NoticeViewDto;
import com.example.demo.entity.File;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import com.example.demo.notice.repository.NoticeRepositoryImpl;
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

    private final NoticeRepositoryImpl noticeRepositoryImpl;


    private final FileService fileService;



    /**
     * method         : saveNotice
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 공지사항 등록
     */

    public void saveNotice(NoticeSaveDto noticeSaveDto) {
        Notice notice = new Notice();

        notice.setMbUserName(noticeSaveDto.getMbUserName());
        notice.setNtTitle(noticeSaveDto.getNtTitle());
        notice.setNtContent(noticeSaveDto.getNtContent());
        notice.setFileSeq(noticeSaveDto.getFileSeq());
        notice.setSeq(noticeSaveDto.getSeq());
        notice.setNtIsView(noticeSaveDto.getNtIsView());
        notice.setNtIsDel("N");
        notice.setNtRegDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }

    /**
     * method         : searchNoticeList
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 공지사항 리스트
     */
    public List<Notice> searchNoticeList() {

        return noticeRepositoryImpl.searchNoticeList();

    }


    /**
     * method         : searchNoticeView
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 공지사항 상세보기
     */

    public NoticeViewDto searchNoticeView(Long seq) {
        NoticeViewDto noticeViewDto = noticeRepositoryImpl.searchNoticeView(seq);

        Notice notice = noticeRepository.findById(seq).get();
        notice.setCount(notice.getCount() + 1);

        File noticeFile = fileService.findById(noticeViewDto.getFileSeq());
        if(noticeFile != null){
            noticeViewDto.setFile(noticeFile);
        }
        return noticeViewDto;
    }


}
