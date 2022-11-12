package bgw.project.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class common {

    @GetMapping("index")
    public void index() {

    }

    @ResponseBody
    @GetMapping(value = "/menuBoardName",produces = "application/json")
    public ResponseEntity menu(){
        //menu를 ajax를통하여 불러올 예정
        return null;
    }
}
