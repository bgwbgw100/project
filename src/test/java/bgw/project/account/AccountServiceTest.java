package bgw.project.account;

import bgw.project.dto.AccountDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void findById(){
        AccountDTO accountDTO = accountMapper.findById("id");
    }

    @Test
    @Transactional
    public void insert(){

        accountMapper.join("id","name","type","au","@aa");
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId("id");
        accountDTO.setName("name");
        accountDTO.setType("type");
        accountDTO.setAuthority("au");
        accountDTO.setEmailAddress("@aa");
        AccountDTO accountDTO2 = accountMapper.findById("id");
        Assertions.assertThat(accountDTO.getId()).isEqualTo(accountDTO2.getId());
        Assertions.assertThat(accountDTO.getName()).isEqualTo(accountDTO2.getName());
        Assertions.assertThat(accountDTO.getName()).isEqualTo(accountDTO2.getName());
        Assertions.assertThat(accountDTO.getAuthority()).isEqualTo(accountDTO2.getAuthority());
        Assertions.assertThat(accountDTO.getEmailAddress()).isEqualTo(accountDTO2.getEmailAddress());

    }


}
