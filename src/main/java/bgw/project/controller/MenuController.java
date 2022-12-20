package bgw.project.controller;

import bgw.project.service.MenuService;
import bgw.project.service.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;

    @GetMapping("menu/insertBoard")
    public String insertBoard() throws Exception {


        return "menu/menu_board_insert";

    }
    @PostMapping("menu/insertBoard")
    public String insertBoardPost(String name,HttpServletRequest request) throws Exception{
        String result = menuService.insertBoardMenu(name,request);

        return "redirect:/index?result="+result;
    }
    @ResponseBody
    @PostMapping("menu/check")
    public Map<String,Object> insertBoardCheck(@RequestBody Map<String, Object> paramMap) throws Exception{
        Object name = paramMap.get("name");
        if(name== null || !StringUtils.hasText(name.toString())){
            HashMap<String, Object> result = new HashMap<>();
            result.put("error", "값을 입력하세요");
            return result;
        }


        Map<String, Object> result = menuService.insertBoardCheck(paramMap);
        return result;
    }


}

