package com.example.demo.util.entity;

import com.example.demo.entity.File;
import com.example.demo.util.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Path;

@Controller
@RequiredArgsConstructor
public class FileDownload {
    private final FileService fileService;
/**
 * method         : fileDownload
 * author         : 오동준
 * date           : 2023/04/24
 * description    : 파일 다운로드
 *
 * 현재 각자 local을 바라보고 작업 중이기 때문에 본인이 파일 첨부한 것만 다운로드 받을수 있습니다.
 * mac은 "//"  윈도우는 "\\" 으로 경로를 구분합니다.
 */
    @GetMapping("/download")
    public void fileDownload(@RequestParam("fileSeq") Long fileSeq, HttpServletResponse response) throws IOException {
        try {
            File file = fileService.findById(fileSeq);
            Path root = Path.of(System.getProperty("user.home"), "uploads");
            String path = root.toString() + "//" + file.getFileMasterCode();
            java.io.File fileio = new java.io.File(path);
            if (fileio.length() > 0) {
                String orgFileName = URLEncoder.encode(file.getFileOriginalName(), "UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-Disposition", "attachment; fileName=\"" + orgFileName + "\";");
                response.setHeader("Content-Transfer-Encoding", "binary");

                FileInputStream fileInputStream = new FileInputStream(path);
                OutputStream out = response.getOutputStream();

                int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                out.flush();
                out.close();
            } else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>");
                out.println("alert('존재하지 않는 파일입니다.');");
                out.println("history.back();");
                out.println("</script>");
                out.flush();
                out.close();
            }
        } catch (IOException ex) {
            // Handle the exception as needed
            ex.printStackTrace();
        }
    }
}
