package at.aau;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiffController {

    @RequestMapping("/mdiff")
    public String index() {
        return "Do something useful here!";
    }

}