package bgw.project.controller;

import bgw.project.dto.BoardDTO;
import bgw.project.form.BoardInsertForm;
import bgw.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("board")
    public String board(){
        return "board/board";
    }

    // 게시판메뉴  만들기
    @GetMapping("makeBoard")
    public String makeBoard(){
        return "board/makeBoard";
    }

    @GetMapping("board/{boardName}/insert")
    public String writerBoard(Model model, @PathVariable("boardName") String boardName) {
        model.addAttribute("boardName",boardName);

        return "board/board_insert";
    }

    @GetMapping("board/{boardName}")
    public String list(Model model, @PathVariable("boardName") String boardName,String page){
        if(page == null){
            page = "1";
        }
        List<BoardDTO> boardDTOS = boardService.boardList(boardName, page);
        model.addAttribute("boardName",boardName);
        model.addAttribute("boardList",boardDTOS);
        model.addAttribute("page",page);

        return "board/board";
    }

    @GetMapping("board/{boardName}/{seq}")
    public String detail(@PathVariable("boardName") String boardName, @PathVariable("seq") int seq,Model model,String page){
        BoardDTO boardDTO = boardService.boardDetail(seq);
        model.addAttribute("board" ,boardDTO);

        return "board/board_detail";
    }
    @PostMapping("board/{boardName}/insert")
    public String insert(@PathVariable("boardName")String boardName , BoardInsertForm boardInsertForm, HttpServletRequest request, Model model) throws Exception {

        boardService.boardInsert(boardInsertForm,request,boardName);
        return "redirect:/board/"+ boardInsertForm.getName();
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
