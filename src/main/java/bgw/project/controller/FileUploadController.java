package bgw.project.controller;

import bgw.project.dto.AttachFileDTO;
import bgw.project.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileUploadController {
    @Value("${file.dir}")
    private String fileDIR;
    private FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/board/upload")
    public ResponseEntity<Map<String,Object>> saveFile(MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String,Object> result = new HashMap<>();
        ResponseEntity<Map<String,Object>> responseEntity;
        String ip = request.getRemoteAddr();


        if (!file.isEmpty()){
            AttachFileDTO attachFileDTO = fileUploadService.imgUpload(file, fileDIR,ip,request);

            result.put("result","success");

            result.put("url","board/img/"+attachFileDTO.getNo());
            result.put("no",attachFileDTO.getNo());
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
            return responseEntity;
        }
        result.put("result","fail");
        responseEntity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @GetMapping("board/img/{no}")
    public byte[] boardImg(@PathVariable String no) throws Exception {
        byte[] imgByte = fileUploadService.imgFileSelect(no);
        return imgByte;
    }



}
