package bgw.project.form;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardInsertForm {


    private String name;
    private String kind;
    private String title;
    private String writer;
    private String content;
    private String noticeStatus;
    private List<String> no;


}
