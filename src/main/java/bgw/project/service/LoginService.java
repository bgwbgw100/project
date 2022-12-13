package bgw.project.service;

import bgw.project.dto.AccountDTO;
import bgw.project.form.AccountSaveForm;
import bgw.project.form.LoginForm;


public interface LoginService {



    public void addAccount(AccountSaveForm accountSaveForm) throws Exception;
    public AccountDTO login(LoginForm loginForm) throws  Exception;

}
