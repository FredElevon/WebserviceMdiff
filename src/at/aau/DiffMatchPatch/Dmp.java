package at.aau.DiffMatchPatch;

import at.aau.DiffInfo;
import at.aau.DiffMain;
import at.aau.DiffMatchPatch.diff_match_patch.Diff;

import java.util.List;


public class Dmp {
    private final List<DiffInfo> dmpInfoList;

    public Dmp(String ori, String rev, int cleanup) {
        DmpMain dmpMain = new DmpMain();

        this.dmpInfoList = dmpMain.diffFiles(ori, rev, cleanup);
    }

    public List<DiffInfo> getDmpInfoList() {
        return dmpInfoList;
    }
}
