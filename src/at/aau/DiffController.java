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
    private String actionType = myList.get(0).getActionType();
    private int srcID = myList.get(0).getSrcID();
    private int srcStartLine = myList.get(0).getSrcStartLine();
    private int srcStartLineOffset = myList.get(0).getSrcStartLineOffset();
    private int srcEndLine = myList.get(0).getSrcEndLine();
    private int srcEndLineOffset = myList.get(0).getSrcEndLineOffset();

    private int dstID = myList.get(0).getDstID();
    private int dstStartLine = myList.get(0).getDstStartLine();
    private int dstStartLineOffset = myList.get(0).getDstStartLineOffset();
    private int dstEndLine = myList.get(0).getDstEndLine();
    private int dstEndLineOffset = myList.get(0).getDstEndLineOffset();

    
    @RequestMapping("/mdiff")

    public MDiff mdiff() {
        return new MDiff(actionType, srcID,srcStartLine,srcStartLineOffset,srcStartLineOffset, srcEndLine,srcEndLineOffset,dstID,dstStartLine,dstStartLineOffset,dstEndLine,dstEndLineOffset);
    }

}