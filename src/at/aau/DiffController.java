package at.aau;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
public class DiffController {

    @RequestMapping(value = "/mdiff", params = "srcID")
    public DiffInfo mdiff(@RequestParam(value = "srcID", defaultValue = "0") int srcID) {
        MDiff mDiff = new MDiff(0, "Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
        return mDiff.getDiffInfoList().get(srcID);
    }

    @RequestMapping("/mdiff")
    public List<DiffInfo> mdiff() {
        MDiff mDiff = new MDiff(0, "Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
        return mDiff.getDiffInfoList();
        //        for (int i = 0; i < mDiff.getSize(); i++) {
//            DiffInfo diffInfoList = mDiff.getDiffInfoList(i);
//            this.diffs.add(diffInfoList);
//        }

//        return Arrays.asList(new MDiff[]{
//                new MDiff(0,"Textfiles/originalFile.txt", "TextFiles/revisedFile.txt"),
//                new MDiff(1,"Textfiles/originalFile.txt", "TextFiles/revisedFile.txt")
//        });  //.getReturnString()
//        new MDiff("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
//        return "test"
    }

    @RequestMapping("/mdiffmydata")
    public List<DiffInfo> mdiffmydata(@RequestParam(value = "file", defaultValue = "originalFile.txt&revisedFile.txt") String file) {
        int symbol = file.indexOf("&");
        String oriFile = file.substring(0,symbol);
        String revFile = file.substring(symbol+1);

        MDiff mDiff = new MDiff(0, "TextFiles/" + oriFile, "TextFiles/" + revFile);
        return mDiff.getDiffInfoList();
    }
}