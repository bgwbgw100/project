package bgw.project.service;


import bgw.project.dto.AccountDTO;
import bgw.project.form.AccountSaveForm;
import bgw.project.form.LoginForm;
import bgw.project.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AccountMapper accountMapper;

    @Override
    public void addAccount(AccountSaveForm accountSaveForm) throws Exception {

    }

    @Override
    public AccountDTO login(LoginForm loginForm) throws  Exception{
        AccountDTO accountDTO = accountMapper.login(loginForm);
        return accountDTO;
    }
}
