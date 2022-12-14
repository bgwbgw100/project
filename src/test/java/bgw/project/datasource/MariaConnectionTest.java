package bgw.project.datasource;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaConnectionTest {

    private static final String Driver = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
    private static final String URL = "jdbc:log4jdbc:mariadb://database-1.cs82q5b9ubms.ap-northeast-2.rds.amazonaws.com:3306/study";
    private static final String USER = "admin";
    private static final String PW = "wjadnf!00";

    @Test
    public void testConnection() throws Exception{
        Class.forName(Driver);
        try(Connection con = DriverManager.getConnection(URL,USER,PW)){
            System.out.println(con);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
