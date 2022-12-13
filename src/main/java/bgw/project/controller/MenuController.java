package bgw.project.controller;

import bgw.project.service.MenuService;
import bgw.project.service.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;

    public String insertBoard(String boardName, HttpServletRequest request) throws Exception {
        menuService.insertBoardMenu(boardName,request);



        return null;

    }
    @ResponseBody
    public String insertBoardCheck() throws Exception{
        return null;
    }


}
