package bgw.project.ApplicationRunner;

import bgw.project.dto.MenuDTO;

import bgw.project.service.MenuServiceImpl;
import bgw.project.string.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import javax.servlet.ServletContext;
import java.util.List;


@Component
@RequiredArgsConstructor
public class MenuApplicationRunner implements ApplicationRunner {
    private final MenuServiceImpl menuService;
    private final ServletContext application;

    @Override
    public void run(ApplicationArguments args) throws Exception {
      //  List<MenuDTO> menuDTOS = menuService.selectAllMenu();
        List<MenuDTO> boardList = menuService.selectMenuBoard();
        application.setAttribute(Const.BOARD_LIST,boardList);
    }
}
