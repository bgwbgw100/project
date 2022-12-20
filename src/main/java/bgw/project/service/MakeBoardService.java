package bgw.project.service;

import bgw.project.dto.MenuDTO;

import java.util.List;
import java.util.Map;

public interface MakeBoardService {
    public Map<String,Object> makeBoard(Map<String,Object> dataMap)throws Exception;
    public Map<String,Object> makeBoardInsert(Map<String,Object> dataMap)throws Exception;
    public Map<String,Object> makeBoardDelete(Map<String,Object> dataMap)throws Exception;

    List<MenuDTO> menuBoardList()throws Exception;
}
