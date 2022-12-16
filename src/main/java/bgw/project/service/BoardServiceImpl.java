package bgw.project.service;


import bgw.project.dto.AccountDTO;
import bgw.project.dto.AttachFileDTO;
import bgw.project.dto.BoardDTO;
import bgw.project.form.BoardInsertForm;
import bgw.project.mapper.AttachFileMapper;
import bgw.project.mapper.BoardMapper;
import bgw.project.mapper.MenuMapper;
import bgw.project.session.SessionConst;
import bgw.project.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final AttachFileMapper attachFileMapper;

    private final MenuMapper menuMapper;


    @Override
    public List<BoardDTO> boardList(String boardName, String page) {

        int total = boardMapper.selectAllCntBoardByName(boardName);
        int nowPage = Integer.parseInt(page);
        int perPage = 10;

        PagingVO pagingVO =  new PagingVO(total, nowPage, perPage);
        pagingVO.setName(boardName);

        List<BoardDTO> boardDTOS = boardMapper.selectAllBoardByPagingVO(pagingVO);

        return boardDTOS;
    }

    @Override
    public BoardDTO boardDetail(int seq) {
        BoardDTO  boardDTO= boardMapper.selectBoardDetailBySeq(seq);
        return boardDTO;
    }

    @Override
    public BoardDTO boardInsert(BoardInsertForm boardInsertForm, HttpServletRequest request, String boardName) throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        AttachFileDTO attachFileDTO = new AttachFileDTO();
        //필터에서 거르기떄문에 세션이 없을수 없음!
        HttpSession session = request.getSession(false);

        AccountDTO accountDTO = (AccountDTO) session.getAttribute(SessionConst.LOGIN_MEMBER);
        int menuId = menuMapper.selectMenuIdByName(boardName);


        boardDTO.setMenuId(menuId);
        boardDTO.setContent(boardInsertForm.getContent());
        boardDTO.setTitle(boardInsertForm.getTitle());
        boardDTO.setWriter(accountDTO.getName());
        boardDTO.setKind(boardInsertForm.getKind());
        boardDTO.setNoticeStatus(boardInsertForm.getNoticeStatus());
        boardDTO.setIp(request.getRemoteAddr());
        boardMapper.insertBoard(boardDTO);


        // 이미지 파일이 있을때
        if(boardInsertForm.getNo()!= null && boardInsertForm.getNo().size()!=0 ){
            List<String> imgNos = boardInsertForm.getNo();
            for (String imgNo : imgNos) {
                attachFileDTO.setWorkPk(String.valueOf(boardDTO.getSeq()));
                attachFileDTO.setTypeWork(boardInsertForm.getName());
                attachFileDTO.setNo(Integer.parseInt(imgNo));
                attachFileMapper.fileWorkTypeUpdate(attachFileDTO);
            }
        }
        return boardDTO;

    }
}
