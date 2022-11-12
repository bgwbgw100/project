package bgw.project.controller;

import bgw.project.dto.BoardDTO;
import bgw.project.service.BoardServiceImpl;
import bgw.project.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("board")
    public String board(){
        return "board/board";
    }

    // 게시판메뉴  만들기
    @GetMapping("makeBoard")
    public String makeBoard(){
        return "board/makeBoard";
    }

    @GetMapping("board/{boardName}")
    public String list(Model model, @PathVariable("boardName") String boardName,String page){
        List<BoardDTO> boardDTOS = boardService.boardList(boardName, page);
        model.addAttribute("boardName",boardName);
        model.addAttribute("boardList",boardDTOS);

        return "board/board?page="+page;
    }

    @GetMapping("board/{boardName}/{seq}")
    public String detail(@PathVariable("boardName") String boardName,@PathVariable("seq") int seq){
        return null;
    }
    @PostMapping("board/{boardName}/insert")
    public String insert(){
        return null;
    }

    @PostMapping("board/{boardName}/update")
    public String update(){
        return null;
    }

    @PostMapping("board/{boardName}/delete")
    public String delete(){
        return null;
    }

}
