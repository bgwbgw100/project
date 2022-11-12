package bgw.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    private String id;
    private String name;
    private String type;
    private String authority;
    private Date dltnDate;
    private Date joinDate;
    private String useOx;
    private String emailAddress;
    private Date lastAccessDate;
    private String password;

}
