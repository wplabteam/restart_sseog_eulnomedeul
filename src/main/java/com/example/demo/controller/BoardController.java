package com.example.demo.controller;


import com.example.demo.dto.NoticeSearchDto;
import com.example.demo.entity.Member;
import com.example.demo.notice.dto.NoticeSaveDto;
import com.example.demo.dto.NoticeViewDto;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.repository.NoticeRepository;
import com.example.demo.service.NoticeService;
import com.example.demo.util.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final NoticeRepository noticeRepository;
    private final FileService fileService;
    private final NoticeService noticeService;


    /**
     * method         : noticeWrite
     * author         : 오동준
     * date           : 2023/04/13
     * description    : 공지사항 작성 페이지
     */
    @RequestMapping("/board/notice/write")
    public String noticeWrite(Model model, Notice noticeSaveDto) {

        model.addAttribute("noticeSaveDto", noticeSaveDto);

        return "board/notice_write";

    }

    /**
     * method         : noticeWrite
     * author         : 오동준
     * date           : 2023/04/13
     * description    : 공지사항 작성
     */
    @PostMapping("/board/notice/write")
    public String noticeWrite(HttpSession session, NoticeSaveDto noticeSaveDto, @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {


        Member member = (Member) session.getAttribute("user");

        noticeSaveDto.setMbUserName(member.getMbUserName());

        // 파일이 있으면 파일을 저장하고 파일 시퀀스를 noticeSaveDto에 담아준다.
        if (!file.isEmpty()) {
            noticeSaveDto.setFileSeq(fileService.saveFile(file));
            noticeService.saveNotice(noticeSaveDto);
        } else {
            // 파일이 없으면 그냥 저장한다.
            noticeService.saveNotice(noticeSaveDto);
        }

        return "redirect:/board/notice/list";
    }

    /**
     * method         : noticeList
     * author         : 오동준
     * date           : 2023/04/13
     * description    : 공지사항 리스트
     */
    @GetMapping("/board/notice/list")
    public String noticeList(Model model, @ModelAttribute("noticeSearchDto") NoticeSearchDto noticeSearchDto) {

        List<Notice> noticeList = noticeService.searchNoticeList(noticeSearchDto);

        model.addAttribute("noticeList", noticeList);
        return "board/notice_list";
    }

    /**
     * method         : noticeDetail
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 공지사항 상세보기
     */

    @GetMapping("/board/notice/view/{seq}")
    public String noticeDetail(@PathVariable Long seq, Model model){
        // 공지사항 상세보기
        NoticeViewDto noticeSaveDto = noticeService.searchNoticeView(seq);

        // noticeSaveDto가 null이면 공지사항 리스트로 이동
        if (noticeSaveDto == null) {
            return "redirect:/board/notice/list";
        } else {
            // 공지사항 상세보기
            model.addAttribute("noticeSaveDto", noticeSaveDto);
            return "board/notice_view";
        }
    }

    @PostMapping("/board/notice/delete")
    public ResponseEntity<?> deleteNotice(@RequestBody Map<String, String> requestBody) {

        Long seq = Long.parseLong(requestBody.get("seq"));

        noticeService.deleteNotice(seq);

        return ResponseEntity.ok(Map.of("result", "success"));

    }

    @RequestMapping("/chart/sum")
    public String getCallSum(){

        return "chart/call_sum";

    }

}
