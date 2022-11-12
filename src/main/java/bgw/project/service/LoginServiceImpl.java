package bgw.project.service;

import bgw.project.dto.AccountDTO;
import bgw.project.form.AccountSaveForm;
import bgw.project.form.LoginForm;
import org.springframework.stereotype.Service;


public interface LoginServiceImpl {



    public void addAccount(AccountSaveForm accountSaveForm) throws Exception;
    public AccountDTO login(LoginForm loginForm) throws  Exception;

}
