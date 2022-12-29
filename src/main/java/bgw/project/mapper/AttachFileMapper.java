package bgw.project.mapper;

import bgw.project.dto.AttachFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttachFileMapper {

    public AttachFileDTO fileSelect(@Param("no") String no) throws Exception;
    public int residueDelete(@Param("seq")int seq) throws Exception;
    public int fileUpdate(AttachFileDTO attachFileDTO) throws Exception;
    public int fileInsert(AttachFileDTO attachFileDTO) throws Exception;
    public int fileDelete(@Param("no") String no) throws Exception;
    public int fileWorkTypeUpdate(AttachFileDTO attachFileDTO) throws Exception;
    public List<String> fileSelectNoByBoardSeq(Map<String, Object> dataMap) throws Exception;

    public int boardFileDeleteByBoardSeq(@Param("seq") int seq)throws Exception;


    void boardUpdateImgDelete(String no);
}
