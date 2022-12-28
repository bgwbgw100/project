package bgw.project.service;


import bgw.project.dto.AccountDTO;
import bgw.project.dto.AttachFileDTO;
import bgw.project.dto.BoardDTO;
import bgw.project.form.BoardInsertForm;
import bgw.project.form.BoardUpdateForm;
import bgw.project.mapper.AttachFileMapper;
import bgw.project.mapper.BoardMapper;
import bgw.project.mapper.MenuMapper;
import bgw.project.session.SessionConst;
import bgw.project.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        PagingVO pagingVO =  new PagingVO();
        pagingVO.perPage(5).total(10).nowPage(nowPage);
        pagingVO.calcResult();

        pagingVO.setName(boardName);

        List<BoardDTO> boardDTOS = boardMapper.selectAllBoardByPagingVO(pagingVO);

        return boardDTOS;
    }

    @Override
    public BoardDTO boardDetail(int seq) {
        BoardDTO boardDTO= boardMapper.selectBoardDetailBySeq(seq);
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
                attachFileDTO.setTypeWork("board");
                attachFileDTO.setNo(Integer.parseInt(imgNo));
                attachFileMapper.fileWorkTypeUpdate(attachFileDTO);
            }
        }
        return boardDTO;

    }
    @Override
    public Map<String,Object> boardDetailImg(int seq) throws Exception{
        BoardDTO boardDTO= boardMapper.selectBoardDetailBySeq(seq);
        Map<String , Object> dataMap= new HashMap<>();
        dataMap.put("seq",seq);
        Map<String, Object> resultMap = new HashMap<>();
        List<String> nos= attachFileMapper.fileSelectNoByBoardSeq(dataMap);
        if(nos !=null){
            resultMap.put("nos",nos);
        }
        resultMap.put("boardDTO",boardDTO);


        return resultMap;
    }
    @Override
    public BoardDTO boardUpdate(BoardUpdateForm boardUpdatesForm, HttpServletRequest request, String boardName) throws Exception {
        BoardDTO.BoardDTOBuilder boardDTOBuilder = BoardDTO.builder();
        AttachFileDTO attachFileDTO = new AttachFileDTO();


        int menuId = menuMapper.selectMenuIdByName(boardName);
        boardDTOBuilder
                .content(boardUpdatesForm.getContent())
                .title(boardUpdatesForm.getTitle())
                .kind(boardUpdatesForm.getKind())
                .ip(request.getRemoteAddr())
                .seq(boardUpdatesForm.getSeq());
        BoardDTO boardDTO = boardDTOBuilder.build();
        boardMapper.updateBoard(boardDTO);


        // 이미지 파일이 있을때
        if(boardUpdatesForm.getNo()!= null && boardUpdatesForm.getNo().size()!=0 ){
            List<String> imgNos = boardUpdatesForm.getNo();
            for (String imgNo : imgNos) {
                attachFileDTO.setWorkPk(String.valueOf(boardUpdatesForm.getSeq()));
                attachFileDTO.setTypeWork("board");
                attachFileDTO.setNo(Integer.parseInt(imgNo));
                attachFileMapper.fileWorkTypeUpdate(attachFileDTO);
            }
        }
        return boardDTO;
    }

    @Override
    public void boardDelete(int seq) throws Exception{
        int boardCnt = boardMapper.deleteBoardBySeq(seq);
        int fileCnt = attachFileMapper.boardFileDeleteByBoardSeq(seq);
    }



}
