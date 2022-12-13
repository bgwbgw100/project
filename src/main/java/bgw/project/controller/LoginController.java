package bgw.project.controller;


import bgw.project.dto.AccountDTO;
import bgw.project.form.AccountSaveForm;
import bgw.project.form.LoginForm;
import bgw.project.service.LoginService;
import bgw.project.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;


    @GetMapping("/login")
    public String loginGet(@RequestParam(required = false) String path,Model model){
        model.addAttribute("path",path);
        return "login";
    }


    @PostMapping("/login")
    public String login(LoginForm loginForm,String path, HttpServletRequest request,RedirectAttributes rttr) throws Exception{
        Boolean pathExistence = StringUtils.hasText(path);
        AccountDTO accountDTO = loginService.login(loginForm);

        
        //아이디 비밀번호 불일치
        if(accountDTO == null){

            rttr.addFlashAttribute("result","fail");
            if(pathExistence){
                return "redirect:/login?path="+path+"&check=false";
            }

            return "redirect:/login";
        }

        // 아이디 비밀번호 일치

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER,accountDTO);
        session.setMaxInactiveInterval(1800);

        if(pathExistence){
            return "redirect:"+path;
        }

        return "redirect:/index";
    }

    @GetMapping("idfind")
    public String idFind(String email){

        return null;
    }
    @GetMapping("pwfind")
    public String pwFind(String id,String email){

        return null;
    }

    @GetMapping("join")
    public String join(AccountSaveForm accountSaveForm,String path) throws Exception {
        loginService.addAccount(accountSaveForm);
        return "/login?path="+path;
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null){
            //쿠키정보를 다들고와서 세션을 다끊을것이라 추정
            session.invalidate();
        }
        return "redirect:/";
    }


}
