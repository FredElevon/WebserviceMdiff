package at.aau;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DiffController {

    @RequestMapping("/mdiff")
    public String mdiff() {
        return new MDiff("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt").getReturnString();
//        new MDiff("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
//        return "test";
    }

}