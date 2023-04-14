package com.example.demo.controller;


import com.example.demo.dto.NoticeSaveDto;
import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeService;
import com.example.demo.util.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
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
     * method         :
     * author         : 오동준
     * date           : 2023/04/13
     * description    : 기능 설명 작성
     */
    @RequestMapping("/board/notice/write")
    public String noticeWrite(Model model, Notice noticeSaveDto) {
        model.addAttribute("noticeSaveDto", noticeSaveDto);

        return "board/notice_write";

    }

    @PostMapping("/board/notice/write")
    public String noticeWrite(Model model, NoticeSaveDto noticeSaveDto, @RequestPart(value="file", required = false)MultipartFile file)  throws Exception  {
        if(!file.isEmpty()){
           noticeSaveDto.setFileSeq(fileService.saveFile(file));
            noticeService.saveNotice(noticeSaveDto);
        }



        return "redirect:/board/notice/write";
    }
}
