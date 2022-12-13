package bgw.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttachFileDTO {
    private int no;
    private String typeWork;
    private String workPk;
    private String writer;
    private String originalFileName;
    private String saveFileName;
    private int downloadCount;
    private long fileSize;
    private String fileExtention;
    private Date rgstrDate;
    private String ip;
    private Date modificationDate;
    private Date dltnDate;
    private String useOx;
    private String path;
}
