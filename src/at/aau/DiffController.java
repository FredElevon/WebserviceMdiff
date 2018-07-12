package at.aau;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DiffController {

    @RequestMapping("/mdiff")
    public MDiff mdiff(@RequestParam(value="srcID", defaultValue="0") int srcID) {
        return new MDiff(srcID,"Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");  //.getReturnString()
//        new MDiff("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
//        return "test"
    }
}