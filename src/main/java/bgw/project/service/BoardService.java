package bgw.project.service;

import bgw.project.dto.BoardDTO;
import bgw.project.form.BoardInsertForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BoardService {

    public List<BoardDTO> boardList(String boardName, String page);
    public BoardDTO boardDetail(int seq);

    BoardDTO boardInsert(BoardInsertForm boardInsertForm, HttpServletRequest request, String boardName) throws Exception;

}
