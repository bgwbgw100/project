package bgw.project.service;

import bgw.project.dto.MenuDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface MenuService {

    public List<MenuDTO> selectAllMenu() throws Exception;
    public Map<String, Object> selectSortByBoard() throws Exception;

    String insertBoardMenu(String boardName, HttpServletRequest request) throws Exception;

    Map<String, Object> insertBoardCheck(String boardName, Map<String, Object> result) throws Exception;

    List<MenuDTO> selectMenuBoard() throws Exception;
}
