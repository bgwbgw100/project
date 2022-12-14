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
            @Result(property = "lastAccessDate",column = "last_access_date"),
            @Result(property = "useOx",column = "use_ox")
    }
    )
    public List<AccountDTO> findAll();

    @Select("select * from account where id = #{id}")
    @Results({
            @Result(property = "joinDate",column = "join_date"),
            @Result(property = "emailAddress",column = "email_address"),
            @Result(property = "dltnDate",column = "dltn_date"),
            @Result(property = "lastAccessDate",column = "last_access_date"),
            @Result(property = "useOx",column = "use_ox")
    })
    public AccountDTO findById(@Param("id") String id);

    @Insert("insert into account (id,name,type,authority,email_address,password) " +
            "values(#{id},#{name},#{type},#{authority},#{email_address},#{password})")
    public void join(@Param("id") String id,@Param("password") String password,@Param("name") String name,@Param("type") String type,@Param("authority") String authority,@Param("email_address") String emailAddress);

    @Update("update account set use_OX ='X',dltn_date=sysdate() where id = #{id}")
    public void delete(@Param("id") String id);

    @Update("update account set name = #{name}, email_address=#{email_address} where id = #{id}")
    public void modifyByNameEmail(@Param("id") String id,@Param("name") String name , @Param("email_address") String emailAddress );

    @Update("update account set password =#{password} where id = #{id}")
    public void modifyByPassword(@Param("id") String id,@Param("password")String password);

    @Update("update account set last_access_date =sysdate() where id = #{id}")
    public void updateLastAccessDate(@Param("id") String id);


}
