package bgw.project.service;

import bgw.project.dto.AttachFileDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileUploadService {
    public AttachFileDTO imgUpload(MultipartFile file, String filePath, String ip, HttpServletRequest request) throws IOException;

    byte[] imgFileSelect(String no) throws Exception;
}
