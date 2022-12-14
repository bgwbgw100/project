package bgw.project.service;

import bgw.project.dto.MenuDTO;
import bgw.project.mapper.MenuMapper;
import bgw.project.string.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        int id = Integer.valueOf(dataMap.get("id").toString());
        //menuMapper.
        return dataMap;
    }
}
