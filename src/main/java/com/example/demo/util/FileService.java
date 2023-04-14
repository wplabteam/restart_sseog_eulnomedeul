package com.example.demo.util;

import com.example.demo.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileRepository fileRepository;

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
}
