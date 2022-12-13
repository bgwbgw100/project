package bgw.project.service;

import bgw.project.dto.AccountDTO;
import bgw.project.dto.AttachFileDTO;
import bgw.project.mapper.AttachFileMapper;
import bgw.project.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final AttachFileMapper attachFileMapper;
    @Override
    public AttachFileDTO imgUpload(MultipartFile file , String filePath, String ip, HttpServletRequest request) {
        String saveFileName = UUID.randomUUID() + file.getOriginalFilename();
        String fullPath = filePath + saveFileName;
        String originalFileName = file.getOriginalFilename();
        HttpSession session = request.getSession(false);
        AccountDTO accountDTO = (AccountDTO) session.getAttribute(SessionConst.LOGIN_MEMBER);

        long fileSize = file.getSize() / 1024;
        String fileExtention = file.getContentType();



        AttachFileDTO attachFileDTO;
        try {
            file.transferTo(new File(fullPath));
            attachFileDTO = new AttachFileDTO();
            attachFileDTO.setTypeWork("board");
            attachFileDTO.setWorkPk("not");
            attachFileDTO.setWriter(accountDTO.getName());
            attachFileDTO.setFileExtention(fileExtention);
            attachFileDTO.setFileSize(fileSize);
            attachFileDTO.setOriginalFileName(originalFileName);
            attachFileDTO.setSaveFileName(saveFileName);
            attachFileDTO.setPath(fullPath);
            attachFileDTO.setIp(ip);
            attachFileMapper.fileInsert(attachFileDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return attachFileDTO;
    }



    @Override
    public byte[] imgFileSelect(String no) throws Exception {

        AttachFileDTO attachFileDTO;
        attachFileDTO = attachFileMapper.fileSelect(no);
        byte[] imgArray = imgByte(attachFileDTO);
        return imgArray;
    };



    private static byte[] imgByte(AttachFileDTO attachFileDTO) throws IOException {
        File file = new File(attachFileDTO.getPath());
        FileInputStream fis = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        fis = new FileInputStream(file);

        int len = 0;
        byte[] buf = new byte[1024];
        while (((len = fis.read(buf)) !=-1)){
            byteArrayOutputStream.write(buf,0,len);
        }
        byte[] fileArray = byteArrayOutputStream.toByteArray();

        return fileArray;
    }

}
