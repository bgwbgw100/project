package bgw.project.account;

import bgw.project.dto.AccountDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapperTest {

    @Select("select * from account")
    @Results({
        @Result(property = "joinDate",column = "join_date"),
        @Result(property = "emailAddress",column = "email_address"),
        @Result(property = "dltnDate",column = "dltn_date"),
        @Result(property = "lastAccessDate",column = "last_access_date")
    }
    )
    public List<AccountDTO> findAll();

    @Select("select * from account where id = #{id}")
    @Results({
            @Result(property = "joinDate",column = "join_date"),
            @Result(property = "emailAddress",column = "email_address"),
            @Result(property = "dltnDate",column = "dltn_date"),
            @Result(property = "lastAccessDate",column = "last_access_date")
    })
    public AccountDTO findById(@Param("id") String id);

    @Insert("insert into account (id,name,type,authority,email_address) " +
            "values(#{id},#{name},#{type},#{authority},#{email_address})")
    public void join(@Param("id") String id,@Param("name") String name,@Param("type") String type,@Param("authority") String authority,@Param("email_address") String emailAddress);
}
