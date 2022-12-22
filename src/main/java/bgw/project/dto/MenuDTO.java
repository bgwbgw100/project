package bgw.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class MenuDTO {
    private int menuId;
    private String name;
    private int sort;
    private int level;
    private String parentName;
    private Date creationDate;
    private String useOx;
    private Date dltnDate;
    private String boardHaveOx;
}

