package com.example.demo.controller;


import com.example.demo.dto.NoticeSaveDto;
import com.example.demo.dto.NoticeViewDto;
import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.service.NoticeService;
import com.example.demo.util.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final NoticeRepository noticeRepository;
    private final FileService fileService;
    private final NoticeService noticeService;

    @RequestMapping("/board/pwChk")
    public String pwChk() {

        return "board/pwChk";

    }

    @PostMapping("/board/pwChk")
    public String pwChk(@RequestParam(value = "password" ,required = false) String password , @RequestParam(value="name" , required = false) String name) {
        System.out.println("name:" + name);
        System.out.println("pw:" + password);
        // 입력한 값 화면에 출력하기

        return "board/pwChk";
    }

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
    public String noticeWrite(Model model, NoticeSaveDto noticeSaveDto, @RequestPart(value="file", required = false)MultipartFile file)  throws Exception  {
        System.out.println("file:" + file);
                if(!file.isEmpty()){
            System.out.println("file:" + file);
           noticeSaveDto.setFileSeq(fileService.saveFile(file));
            noticeService.saveNotice(noticeSaveDto);
        }

        return "redirect:/board/notice/write";
    }

    /**
     * method         : noticeList
     * author         : 오동준
     * date           : 2023/04/13
     * description    : 공지사항 리스트
     */
    @GetMapping("/board/notice/list")
    public String noticeList(Model model) {

        List<Notice> noticeList = noticeService.searchNoticeList();

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
        NoticeViewDto noticeSaveDto = noticeService.searchNoticeView(seq);
        model.addAttribute("noticeSaveDto", noticeSaveDto);
        return "board/notice_view";
    }

    @PostMapping("/board/notice/delete")
    public ResponseEntity<?> deleteNotice(@RequestBody Map<String, String> requestBody){

        Long seq = Long.parseLong(requestBody.get("seq"));

        Notice notice = noticeRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. seq=" + seq));
        if(notice == null){
            return ResponseEntity.badRequest().body("해당 게시글이 없습니다.");
        }

        notice.setNtIsDel("Y");
        noticeRepository.save(notice);

        return ResponseEntity.ok(Map.of("result", "success"));

    }

}
