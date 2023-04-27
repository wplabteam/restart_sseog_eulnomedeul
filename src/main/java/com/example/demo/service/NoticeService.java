package com.example.demo.service;

import com.example.demo.dto.NoticeSearchDto;
import com.example.demo.notice.dto.NoticeSaveDto;
import com.example.demo.dto.NoticeViewDto;
import com.example.demo.entity.File;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import com.example.demo.notice.repository.NoticeRepositoryImpl;
import com.example.demo.util.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

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
    public List<Notice> searchNoticeList(NoticeSearchDto noticeSearchDto) {

        return noticeRepositoryImpl.searchNoticeList(noticeSearchDto);

    }


    /**
     * method         : searchNoticeView
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 공지사항 상세보기
     */

    public NoticeViewDto searchNoticeView(Long seq){
        NoticeViewDto noticeViewDto = noticeRepositoryImpl.searchNoticeView(seq);

        // url 변경으로 is_del 게시글일 경우 null 반환
        if (noticeViewDto != null && noticeViewDto.getNtIsDel().equals("Y")) {
            return null;
        }

        // noticeViewDto 가 null 이 아닐 경우
        if (noticeViewDto != null) {
            try {
                // 조회수 증가
                Notice notice = noticeRepository.findById(seq)
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq=" + seq));
                notice.setCount(noticeViewDto.getCount() + 1);
                noticeRepository.save(notice);

                // 파일 조회
                File noticeFile = fileService.findById(noticeViewDto.getFileSeq());
                // 파일이 있을 경우
                if (noticeFile != null) {
                    noticeViewDto.setFile(noticeFile);
                } else {
                    // 파일이 없을 경우
                    noticeViewDto.setFile(null);
                }
            } catch (Exception e) {
                // 기타 예외 처리
                e.printStackTrace();
            }
            return noticeViewDto;
        }
        return null;
    }



    public void deleteNotice(Long seq) {

        Notice notice = noticeRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq=" + seq));
        notice.setNtIsDel("Y");
        noticeRepository.save(notice);
    }
}
