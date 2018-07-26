package at.aau.DiffMatchPatch;

import at.aau.DiffInfo;
import at.aau.DiffMatchPatch.diff_match_patch.Diff;

import java.util.List;

import static at.aau.DiffMatchPatch.DmpMain.diffFiles;



public class Dmp {
    private final List<DiffInfo> dmpInfoList;

    public Dmp(String ori, String rev, int cleanup) {
        this.dmpInfoList = diffFiles(ori, rev, cleanup);
    }

    public List<DiffInfo> getDmpInfoList() {
        return dmpInfoList;
    }
}
