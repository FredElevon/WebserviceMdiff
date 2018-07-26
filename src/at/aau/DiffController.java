package at.aau;

import at.aau.DiffMatchPatch.Dmp;
import at.aau.DiffMatchPatch.diff_match_patch;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DiffController {

    @RequestMapping(value = "/mdiff", params = "srcID")
    public DiffInfo mdiff(@RequestParam(value = "srcID", defaultValue = "0") int srcID) {
        MDiff mDiff = new MDiff("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
        return mDiff.getDiffInfoList().get(srcID);
    }

    @RequestMapping("/mdiff")
    public List<DiffInfo> mdiff() {
        MDiff mDiff = new MDiff("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
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

    @RequestMapping(value = "/mdiffmydata/{oriFile}/{revFile}", method = RequestMethod.GET)

    public List<DiffInfo> mdiffmydata(@PathVariable("oriFile") String oriFile,
                                      @PathVariable("revFile") String revFile) {

        MDiff mDiff = new MDiff("TextFiles/" + oriFile, "TextFiles/" + revFile);
        return mDiff.getDiffInfoList();
    }

    @RequestMapping(value = "/dmpdata/{oriFile}/{revFile}/{cleanUp}", method = RequestMethod.GET)

    public List<DiffInfo> dmp(@PathVariable("oriFile") String oriFile,
                                           @PathVariable("revFile") String revFile,
                                           @PathVariable("cleanUp") int cleanUp) {

        Dmp dmp = new Dmp("TextFiles/" + oriFile, "TextFiles/" + revFile, cleanUp);
        return dmp.getDmpInfoList();
    }
}