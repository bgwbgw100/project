package bgw.project.service;

import bgw.project.dto.MenuDTO;
import bgw.project.mapper.MenuMapper;
import bgw.project.string.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MakeBoardServiceImpl implements MakeBoardService{

    private final MenuMapper menuMapper;

    @Override
    public Map<String, Object> makeBoard(Map<String, Object> dataMap) throws Exception {

        return dataMap;
    }

    @Override
    public Map<String, Object> makeBoardInsert(Map<String, Object> dataMap) throws Exception {
        // 비었는지 확인 나중에 검증 바꿀예정
        
        if(dataMap.get("name")==null){
            dataMap.put("error","데이터가 없습니다");
            return  dataMap;
        }
        String name = String.valueOf(dataMap.get("name"));

        // 중복 데이터 확인
        MenuDTO menuDTO =  new MenuDTO();
        menuDTO.setParentName(Const.BOARD);
        menuDTO.setName(name);

        int cnt =menuMapper.selectMenuCountByNameParentName(menuDTO);

        if(cnt==0){
            dataMap.put("error","중복입니다.");
            return  dataMap;
        }


        int sort = menuMapper.selectMenuSortMax(Const.BOARD);



        menuDTO.setLevel(2);
        menuDTO.setSort(sort);

        menuMapper.insertMenu(menuDTO);

        return dataMap;
    }

    @Override
    public Map<String, Object> makeBoardDelete(Map<String, Object> dataMap) throws Exception {
        MenuDTO menuDTO = menuMapper.selectMenuBoardHaveBoardCheck(dataMap);
        String[] str;
        dataMap.put("parentName","board");
        int result;
        if(menuDTO != null){
            result = menuMapper.updateUseOxDelete();
            str = (result == 0) ? new String[]{"fail", "삭제에 실패했습니다."} : new String[]{"success", "삭제 성공."};
            dataMap.put(str[0],str[1]);
            return dataMap;
        }

        result = menuMapper.deleteMenuByNameAndParentName(dataMap);
        str = (result == 0) ? new String[]{"fail", "삭제에 실패했습니다."} : new String[]{"success", "삭제 성공."};
        dataMap.put(str[0],str[1]);
        return dataMap;
    }
    @Override
    public List<MenuDTO> menuBoardList()throws Exception{

        List<MenuDTO> menuDTOS = menuMapper.selectAllMenuBoard();


        for (MenuDTO menu: menuDTOS) {
            Map<String,Object> map = new HashMap<>();
            map.put("name",menu.getName());
            MenuDTO menuDTO = menuMapper.selectMenuBoardHaveBoardCheck(map);
            String haveOx ;
            if(menuDTO != null) haveOx = "O";
            else haveOx = "X";
            menu.setBoardHaveOx(haveOx);
        }
        return menuDTOS;
    }
}
