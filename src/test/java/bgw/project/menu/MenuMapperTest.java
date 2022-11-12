package bgw.project.menu;

import bgw.project.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapperTest {
    public int insertMenu(@Param("name") String name,@Param("sort") int sort,@Param("level") int level, @Param("parent_name") String parentName);
    public List<MenuDTO> selectAllMenu();
    public MenuDTO selectMenuByName(@Param("name") String name);
    public int selectAllMenuCnt();
}
