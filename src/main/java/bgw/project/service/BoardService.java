package bgw.project.service;


import bgw.project.dto.BoardDTO;
import bgw.project.mapper.BoardMapper;
import bgw.project.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardServiceImpl{

    private final BoardMapper boardMapper;


    @Override
    public List<BoardDTO> boardList(String boardName, String page) {
        int total = boardMapper.selectAllCntBoardByName(boardName);
        int nowPage = Integer.parseInt(page);
        int perPage = 5;

        PagingVO pagingVO =  new PagingVO(total, nowPage, 5);
        pagingVO.setName(boardName);

        List<BoardDTO> boardDTOS = boardMapper.selectAllBoardByPagingVO(pagingVO);

        return boardDTOS;
    }

    @Override
    public BoardDTO boardDetail(int seq) {
        return null;
    }
}
