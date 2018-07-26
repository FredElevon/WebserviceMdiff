package at.aau.DiffMatchPatch;

import at.aau.DiffInfo;
import at.aau.DiffMatchPatch.diff_match_patch;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DmpMain {

    private String srcText;
    private String dstText;


    private void readFile(String src, String dst) {
        try (FileInputStream inputStream = new FileInputStream(src)) {
            String srcText = IOUtils.toString(inputStream);
            this.srcText = srcText;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream inputStream = new FileInputStream(dst)) {
            String dstText = IOUtils.toString(inputStream);
            this.dstText = dstText;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<DiffInfo> diffFiles(String srcFilename, String dstFilename, int cleanup) {
        DmpMain dmpMain = new DmpMain();
        dmpMain.readFile(srcFilename, dstFilename);

        diff_match_patch dmp = new diff_match_patch();

        LinkedList<diff_match_patch.Diff> dpmListe = dmp.diff_main(dmpMain.srcText, dmpMain.dstText);

        if (cleanup == 1) {
            dmp.diff_cleanupSemantic(dpmListe);
        } else if (cleanup == 2) {
            dmp.diff_cleanupEfficiency(dpmListe);
        } else {
            return null;
        }

        ArrayList<diff_match_patch.Diff> dmpBufferList = new ArrayList<>(dpmListe);
        ArrayList<DiffInfo> diffList = new ArrayList<>();

        int diffListSize = dmpBufferList.size();
        System.out.println(diffListSize);


        for (int i = 0; i <= diffListSize; i++) {
            DiffInfo diffInfo = new DiffInfo();

            diff_match_patch.Operation getOperation = dmpBufferList.get(i).operation;
            if (getOperation.equals("EQUAL")) {
                continue;
            } else if (getOperation.equals("INSERT")) {
                diffInfo.setActionType("INSERT");
            } else if (getOperation.equals("DELETE")) {
                diffInfo.setActionType("DELETE");
            }

            diffList.add(diffInfo);
        }


        return diffList;
    }
}
