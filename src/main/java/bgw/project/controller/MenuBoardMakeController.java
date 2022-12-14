package bgw.project.controller;

import bgw.project.service.MakeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("makeBoard")
public class MenuBoardMakeController {
    private final MakeBoardService makeBoardService;
    @GetMapping
    public String makeBoard(@RequestParam Map<String, Object> paramMap) throws Exception{

        return "makeBoard";

    }
    @PostMapping
    public String makeBoardInsert(@RequestBody Map<String, Object> paramMap, Model model) throws Exception{
        makeBoardService.makeBoardInsert(paramMap);
        if(null != paramMap.get("error")){
            // exception 던지기
        }
        return "makeBoard";
    }

    @PostMapping
    @ResponseBody
    public Map<String,Object> makeBoardDelete(@RequestBody Map<String, Object> paramMap, Model model) throws Exception{
        makeBoardService.makeBoardDelete(paramMap);

        return paramMap;
    }


}
