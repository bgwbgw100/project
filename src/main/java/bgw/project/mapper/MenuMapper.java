package bgw.project.mapper;

import bgw.project.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    public List<MenuDTO> selectAllMenu() throws Exception;
    public List<String> selectAllMenuName() throws Exception;
    public int insertMenu(MenuDTO menuDTO) throws Exception;
    public List<Integer> selectSortByParentName(@Param("parentName")String parentName) throws Exception;
    public int selectMenuSortMax(@Param("parentName") String parentName) throws Exception;
    public int selectMenuCountByName(@Param("name") String Name) throws  Exception;
    public int selectMenuCountByNameParentName(MenuDTO menuDTO) throws  Exception;
}
