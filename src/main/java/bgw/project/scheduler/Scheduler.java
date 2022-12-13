package bgw.project.scheduler;

import bgw.project.dto.MenuDTO;
import bgw.project.service.MenuServiceImpl;
import bgw.project.string.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final MenuServiceImpl menuService;
    private final ServletContext application;

    @Scheduled(fixedDelay = 1800*1000,initialDelay = 1800*1000)
    public void menuScheduler() throws Exception {
        List<MenuDTO> menuDTOS = menuService.selectAllMenu();
        application.setAttribute( Const.MENU_LIST,menuDTOS);
    }
}
