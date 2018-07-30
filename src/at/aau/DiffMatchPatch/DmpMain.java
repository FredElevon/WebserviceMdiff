package at.aau.DiffMatchPatch;

import at.aau.DiffInfo;
import at.aau.DiffMatchPatch.diff_match_patch;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DmpMain {

    private List<String> srcList;
    private List<String> dstList;

    public static List<String> fileToLines(String fileName) {
        ArrayList<String> liste = new ArrayList<>();

        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(liste::add);
        }  catch (IOException ex) {
            System.out.println("Can not read file: " + fileName);
        }
        return liste;
    }

    private void readFile(String src, String dst) {
//        try (FileInputStream inputStream = new FileInputStream(src)) {
//            String srcList = IOUtils.toString(inputStream);
//            this.srcList = srcList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (FileInputStream inputStream = new FileInputStream(dst)) {
//            String dstList = IOUtils.toString(inputStream);
//            this.dstList = dstList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        srcList = fileToLines(src);
        dstList = fileToLines(dst);

    }


    public static List<DiffInfo> diffFiles(String srcFilename, String dstFilename, int cleanup) {
        DmpMain dmpMain = new DmpMain();
        dmpMain.readFile(srcFilename, dstFilename);

        diff_match_patch dmp = new diff_match_patch();

        System.out.println(dmpMain.srcList.get(1));


        LinkedList<diff_match_patch.Diff> dpmListe;



//        int diffListSize;
//        if (dmpMain.srcList.size() < dmpMain.dstList.size()) {
//            diffListSize = dmpMain.dstList.size();
//        } else {
//            diffListSize = dmpMain.srcList.size();
//        }

        ArrayList<DiffInfo> diffList = new ArrayList<>();

        LinkedList<diff_match_patch.Diff> fullDiff = dmp.diff_main(String.join("\n", dmpMain.srcList), String.join("\n", dmpMain.dstList));

        switch (cleanup) {
            case 1:
                dmp.diff_cleanupSemantic(fullDiff);
                break;
            case 2:
                dmp.diff_cleanupEfficiency(fullDiff);
                break;
            default:
                System.out.println("No cleanup. Raw Data!");
                break;
        }

        int fullDiffSize = fullDiff.size();

        for (int i = 0; i <= fullDiffSize; i++) {
            DiffInfo diffInfo = new DiffInfo();

            dpmListe = dmp.diff_main(dmpMain.srcList.get(i), dmpMain.dstList.get(i));

            // Is cleanup Parameter set and what type


            // Convert LinkedList(dmpListe) to ArrayList
            ArrayList<diff_match_patch.Diff> dmpArrayList = new ArrayList<>(dpmListe);

            for (diff_match_patch.Diff diff : dmpArrayList) {
                switch (diff.operation) {
                    case EQUAL:
                        break;
                    case DELETE:
                        diffInfo.setActionType("DELETE");
                        break;
                    case INSERT:
                        diffInfo.setActionType("INSERT");
                        break;
                    default:
                        System.out.println("Failed to setActionType: DmpMain, Line ca. 103-113");
                }

            }

            diffInfo.setSrcID(i);

            diffList.add(diffInfo);
        }


        return diffList;
    }


}
