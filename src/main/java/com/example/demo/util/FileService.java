package com.example.demo.util;

import com.example.demo.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    /**
     * method         : saveFile
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 파일 첨부 저장
     */
    public Long saveFile(MultipartFile multipartFile) throws Exception {
        String originalFileName = multipartFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        String mimeType = multipartFile.getContentType();
        String saveCode = UniqueCodeUtile.getUniqueNumber().concat(ext);

        Path root = Paths.get(System.getProperty("user.home"), "uploads");
        Files.createDirectories(root);

        Path target = root.resolve(saveCode); // 확장자명과 같이저장

        Files.write(target, multipartFile.getBytes());

        File file = File.builder()
                .fileMasterCode(saveCode)
                .fileOriginalName(originalFileName)
                .fileType(mimeType)
                .regDate(LocalDateTime.now())
                .build();

        file = fileRepository.save(file);
        return file.getSeq();
    }


    /**
     * method         : findById
     * author         : 오동준
     * date           : 2023/04/18
     * description    : 파일 찾기
     */

    public File findById(Long fileSeq) {

        return fileRepository.findById(fileSeq).orElseThrow(() -> new IllegalArgumentException("해당 파일이 없습니다."));
    }
}
