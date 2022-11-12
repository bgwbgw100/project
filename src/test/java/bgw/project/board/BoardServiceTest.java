package bgw.project.board;

import bgw.project.dto.BoardDTO;
import bgw.project.dto.MenuDTO;
import bgw.project.menu.MenuMapperTest;
import bgw.project.vo.PagingVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Service
@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardMapperTest boardMapperTest;

    @Autowired
    MenuMapperTest menuMapperTest;

    @Test
    @Transactional
    public void writerBoard(){
        int seq;
        String name = "testBoard";
        String kind = "kind";
        String title = "title";
        String writer = "writer";
        String content = "내용";
        String noticeStatus = "X";
        String ip = "ip";
        boolean nameEquals;
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setName(name);
        boardDTO.setKind(kind);
        boardDTO.setTitle(title);
        boardDTO.setWriter(writer);
        boardDTO.setContent(content);
        boardDTO.setNoticeStatus(noticeStatus);
        boardDTO.setIp(ip);

        MenuDTO menuDTO = menuMapperTest.selectMenuByName("testBoard");


        nameEquals = menuDTO.getName().equals(name);
        assertThat(nameEquals).isEqualTo(true);

        seq = boardMapperTest.insertBoard(boardDTO);

        BoardDTO boardDTO2 = boardMapperTest.selectBoardDetailBySeq(boardDTO.getSeq());

        assertThat(boardDTO2.getName()).isEqualTo(name);
        assertThat(boardDTO2.getKind()).isEqualTo(kind);
        assertThat(boardDTO2.getTitle()).isEqualTo(title);
        assertThat(boardDTO2.getWriter()).isEqualTo(writer);
        assertThat(boardDTO2.getContent()).isEqualTo(content);
        assertThat(boardDTO2.getNoticeStatus()).isEqualTo(noticeStatus);
        assertThat(boardDTO2.getIp()).isEqualTo(ip);

    };

    @Test
    @Transactional
    public void update(){
        int update;
        boolean result;
        int seq = 140;

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setSeq(140);
        boardDTO.setTitle("updateTitle");
        boardDTO.setContent("updateContent");
        boardDTO.setKind("updateKind");
        boardDTO.setIp("updateIp");


        update = boardMapperTest.updateBoard(boardDTO);
        result = (update > 0);

        assertThat(result).isEqualTo(true);
    }

    @Test
    @Transactional
    public void delete(){
        int delete;

        BoardDTO boardDTO;
        int seq = 140;

        delete =boardMapperTest.deleteBoard(seq);
        boardDTO = boardMapperTest.selectBoardDetailBySeq(seq);

        assertThat(delete).isEqualTo(1);
        assertThat(boardDTO.getUseOx()).isEqualTo("X");
    }

    @Test
    public void selectAll(){
        int total;
        int nowPage =1;
        int perPage =2;
        String name = "testBoard";


        total = boardMapperTest.selectAllCntBoardByName(name);
        PagingVO pagingVO = new PagingVO(total,nowPage,perPage);
        pagingVO.setName(name);
    
        

        List<BoardDTO> boardDTOS =  boardMapperTest.selectAllBoardByPagingVO(pagingVO);
        //마리아 db 첫 실행이 데이터가 안불러와짐 왜인지 모르겟음
        boardDTOS =  boardMapperTest.selectAllBoardByPagingVO(pagingVO);

    }


}
