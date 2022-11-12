package bgw.project.menu;

import bgw.project.dto.MenuDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Service
public class MenuServiceTest {

    private MenuMapperTest menuMapperTest;

    @Autowired
    public MenuServiceTest(MenuMapperTest menuMapperTest){
        this.menuMapperTest = menuMapperTest;
    }


    @Test
    @Transactional
    @DisplayName("메뉴 생성")
    public void insert() {
        System.out.println(menuMapperTest);

        String name = "name";
        int sort = 1;
        int level = 1;
        String parentName = "parentName";

        int result = menuMapperTest.insertMenu(name,sort,level,parentName);
        assertThat(result).isEqualTo(1);

    }
    
    @Test
    @Transactional
    @DisplayName("메뉴 확인")
    public void selectAll(){

        List<MenuDTO> menuDTOs = menuMapperTest.selectAllMenu();
        int result = menuMapperTest.selectAllMenuCnt();

        assertThat(menuDTOs.size()).isEqualTo(result);

    }




}

