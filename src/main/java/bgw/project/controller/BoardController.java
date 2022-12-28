package bgw.project.controller;

import bgw.project.dto.BoardDTO;
import bgw.project.form.BoardInsertForm;
import bgw.project.form.BoardUpdateForm;
import bgw.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @GetMapping("board/{boardName}/{page}")
    public String list(Model model, @PathVariable("boardName") String boardName,@PathVariable(value = "page",required = false)String page){
        if(page == null){
            page = "1";
        }
        List<BoardDTO> boardDTOS = boardService.boardList(boardName, page);
        model.addAttribute("boardName",boardName);
        model.addAttribute("boardList",boardDTOS);
        model.addAttribute("page",page);

        return "board/board";
    }

    @GetMapping("board/{boardName}/detail/{seq}")
    public String detail(@PathVariable("boardName") String boardName, @PathVariable("seq") int seq,Model model,String page){
        BoardDTO boardDTO = boardService.boardDetail(seq);
        model.addAttribute("board" ,boardDTO);

        return "board/board_detail";
    }
    @GetMapping("board/{boardName}/update/{seq}")
    public String update(@PathVariable("boardName") String boardName, @PathVariable("seq") int seq,Model model,String page) throws Exception{
        Map<String,Object> resultMap = boardService.boardDetailImg(seq);
        BoardDTO boardDTO = (BoardDTO) resultMap.get("boardDTO");
        if(resultMap.get("nos")!= null){
            List<String> nos =  (List<String>) resultMap.get("nos");
            model.addAttribute("no" ,nos);
        }

        model.addAttribute("board" ,boardDTO);


        return "board/board_update";
    }

    @PostMapping("board/{boardName}/insert")
    public String insert(@PathVariable("boardName")String boardName ,BoardInsertForm boardInsertForm, HttpServletRequest request, Model model) throws Exception {

        boardService.boardInsert(boardInsertForm,request,boardName);
        return "redirect:/board/"+ boardInsertForm.getName();
    }

    @PostMapping("board/{boardName}/update")
    public String update(@PathVariable("boardName")String boardName , BoardUpdateForm boardUpdateForm, HttpServletRequest request, Model model) throws Exception{

        boardService.boardUpdate(boardUpdateForm,request,boardName);
        return "redirect:/board/"+boardName+"/detail/"+boardUpdateForm.getSeq();
    }

    @PostMapping("board/{boardName}/delete/{seq}")
    public String delete(@PathVariable("boardName")String boardName, @PathVariable("seq") int seq,String page) throws Exception {
        boardService.boardDelete(seq);
        return  "redirect:/board/"+boardName;
    }

}
