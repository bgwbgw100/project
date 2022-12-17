package bgw.project.controller;

import bgw.project.service.MenuService;
import bgw.project.service.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;

    @GetMapping("menu/insertBoard")
    public String insertBoard(String boardName, HttpServletRequest request) throws Exception {


        return "menu/menu_board_insert";

    }
    @PostMapping("menu/insertBoard")
    public String insertBoardPost(String boardName,HttpServletRequest request) throws Exception{
        String result = menuService.insertBoardMenu(boardName,request);

        return "redirect://index?result="+result;
    }

    @ResponseBody
    @PostMapping("menu/check")
    public String insertBoardCheck() throws Exception{
        return "aa";
    }


}
