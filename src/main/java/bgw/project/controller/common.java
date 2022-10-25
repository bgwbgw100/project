package bgw.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class common {

    @GetMapping("index")
    public void index() {

    }
}
