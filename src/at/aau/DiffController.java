package at.aau;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static at.aau.DiffMain.diffFiles;

@RestController
public class DiffController {

    List<DiffInfo> myList = diffFiles("originalFile.txt", "revisedFile.txt");
    private String template = myList.get(0).getActionType();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/mdiff")

    public MDiff mdiff() {
        return new MDiff(counter.incrementAndGet(), String.format(template));
    }

}