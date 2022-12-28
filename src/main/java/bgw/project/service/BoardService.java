package bgw.project.service;

import bgw.project.dto.BoardDTO;
import bgw.project.form.BoardInsertForm;
import bgw.project.form.BoardUpdateForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BoardService {

    public List<BoardDTO> boardList(String boardName, String page);
    public BoardDTO boardDetail(int seq);

    BoardDTO boardInsert(BoardInsertForm boardInsertForm, HttpServletRequest request, String boardName) throws Exception;

    Map<String,Object> boardDetailImg(int seq) throws Exception;

    BoardDTO boardUpdate(BoardUpdateForm boardUpdatesForm, HttpServletRequest request, String boardName) throws Exception;

    void boardDelete(int seq) throws Exception;
}
