package bgw.project.service;

import bgw.project.dto.MenuDTO;
import bgw.project.mapper.MenuMapper;
import bgw.project.string.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuMapper menuMapper;


    @Override
    public List<MenuDTO> selectAllMenu() throws Exception {

        List<MenuDTO> menuDTOS = menuMapper.selectAllMenu();


        return menuDTOS;
    }

    @Override
    public Map<String, Object> selectSortByBoard() throws Exception {
        Map<String, Object> result = new LinkedHashMap<>();
        List<Integer> sortList = menuMapper.selectSortByParentName("board");
        result.put("boardSortList",sortList);

        return result;
    }

    @Override
    public String insertBoardMenu(String boardName, HttpServletRequest request) throws Exception {
        int menuCount = menuMapper.selectMenuCountByName(boardName);
        if(menuCount>0){
            return "이미 있는 게시판 입니다.";
        }
        int sortMax = menuMapper.selectMenuSortMax("board");
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setName(boardName);
        menuDTO.setSort(sortMax+1);
        menuDTO.setLevel(2);
        menuDTO.setParentName("board");

        menuMapper.insertMenu(menuDTO);
        List<MenuDTO> menuDTOS = menuMapper.selectAllMenu();


        ServletContext application = request.getServletContext();
        application.setAttribute(Const.BOARD_LIST,menuDTOS);
        return "성공";
    }

    @Override
    public  Map<String, Object> insertBoardCheck(Map<String, Object> paramMap) throws Exception {
        Map<String, Object> result = new HashMap<>();

        int menuCount = menuMapper.selectMenuCountByName(paramMap.get("name").toString());


        if(menuCount>0){
            result.put("error","중복 입니다.");
            return result;
        }
        result.put("success","성공!");

        return result;
    }

    @Override
    public List<MenuDTO> selectMenuBoard() throws Exception {
        List<MenuDTO> boardList = menuMapper.selectMenuBoard();
        return boardList;
    }


}
