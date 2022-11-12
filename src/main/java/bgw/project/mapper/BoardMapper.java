package bgw.project.mapper;

import bgw.project.dto.BoardDTO;
import bgw.project.vo.PagingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    public int selectAllCntBoardByName(@Param("name") String name);
    public List<BoardDTO> selectAllBoardByPagingVO(PagingVO pagingVO);
    public BoardDTO selectBoardDetailBySeq(@Param("seq") int seq);
    public int insertBoard(BoardDTO boardDTO);
    public int updateBoard(BoardDTO boardDTO);
    public int deleteBoard(int seq);

}
