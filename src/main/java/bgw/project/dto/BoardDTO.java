package bgw.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int seq;
    private int order;
    private int menuId;
    private String kind;
    private String title;
    private String writer;
    private String content;
    private String noticeStatus;
    private int views;
    private int rn;
    private Date rgstrDate;
    private Date mdfctDate;
    private Date dltnDate;
    private String useOx;
    private String ip;

}
