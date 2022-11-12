package bgw.project.account;

import bgw.project.dto.AccountDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Service
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountMapperTest accountMapper;

    @Test
    public void findAll(){
        List<AccountDTO> accountDTOS = accountMapper.findAll();

    }

    @Test
    public AccountDTO findById(){
        AccountDTO accountDTO = accountMapper.findById("id");
        return accountDTO;
    }

    @Test
    @Transactional
    @DisplayName("아이디 생성")
    public void insert(){

        accountMapper.join("id","1234","name","type","au","@aa");
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId("id");
        accountDTO.setName("name");
        accountDTO.setPassword("1234");
        accountDTO.setAuthority("au");
        accountDTO.setEmailAddress("@aa");
        AccountDTO accountDTO2 = accountMapper.findById("id");
        assertThat(accountDTO.getId()).isEqualTo(accountDTO2.getId());
        assertThat(accountDTO.getPassword()).isEqualTo(accountDTO2.getPassword());
        assertThat(accountDTO.getName()).isEqualTo(accountDTO2.getName());
        assertThat(accountDTO.getAuthority()).isEqualTo(accountDTO2.getAuthority());
        assertThat(accountDTO.getEmailAddress()).isEqualTo(accountDTO2.getEmailAddress());
    }
    @Test
    @Transactional
    @DisplayName("아이디 삭제")
    public void delete(){
        insert();
        accountMapper.delete("id");
        AccountDTO accountDTO= findById();
        assertThat(accountDTO.getUseOx()).isEqualTo("X");

    }
    @Test
    @Transactional
    @DisplayName("이름 이메일수정")
    public void modify(){
        insert();
        accountMapper.modifyByNameEmail("id", "modi","@mody");
        AccountDTO accountDTO= findById();
        assertThat(accountDTO.getName()).isEqualTo("modi");
        assertThat(accountDTO.getEmailAddress()).isEqualTo("@mody");

    }




}
