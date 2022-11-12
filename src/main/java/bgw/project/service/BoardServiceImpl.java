package bgw.project.service;

import bgw.project.dto.BoardDTO;
import bgw.project.vo.PagingVO;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardServiceImpl {

    public List<BoardDTO> boardList(String boardName, String page);
    public BoardDTO boardDetail(int seq);

}
