package at.aau.DiffMatchPatch;

import at.aau.DiffInfo;
import at.aau.DiffMatchPatch.diff_match_patch;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

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

    static ArrayList<Integer> newlinePos;

    public ArrayList<Integer> getNewlinePos() {
        return newlinePos;
    }

    public static List<String> fileToLines(String fileName) {
        ArrayList<String> liste = new ArrayList<>();

        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(liste::add);
        } catch (IOException ex) {
            System.out.println("Can not read file: " + fileName);
        }
        return liste;
    }

//    private void readFileToString(String srcFilename, String dstFilename) {
//        try (FileInputStream inputStream = new FileInputStream(srcFilename)) {
//            String srcList = IOUtils.toString(inputStream);
//            this.srcText = srcList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (FileInputStream inputStream = new FileInputStream(dstFilename)) {
//            String dstList = IOUtils.toString(inputStream);
//            this.dstText = dstList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void readFile(String srcFilename, String dstFilename) {

        srcList = DmpMain.fileToLines(srcFilename);
        dstList = DmpMain.fileToLines(dstFilename);
    }


    public List<DiffInfo> diffFiles(String srcFilename, String dstFilename, int cleanup) {
        this.readFile(srcFilename, dstFilename);

        diff_match_patch dmp = new diff_match_patch();

        System.out.println(this.srcList.get(1));


        ArrayList<DiffInfo> diffList = new ArrayList<>();

        LinkedList<diff_match_patch.Diff> fullDiff = dmp.diff_main(String.join("\n", srcList), String.join("\n", dstList));

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
        newlinePos = new ArrayList<>();

        Integer srcZeile = 1;
        Integer srcOffset = 0;

        Integer dstZeile = 1;
        Integer dstOffset = 0;

        int id = 0;

        for (int i = 0; i < fullDiffSize; i++) {

            DiffInfo diffInfo;
            diffInfo = new DiffInfo();



//            for (String s : fullDiff.get(i).text.split("\n")) {
//                System.out.println(s.length());
//                newlinePos.add(s.length());
//            }


//            for (int j = -1; (j = fullDiff.get(i).text.indexOf("\n", j + 1)) != -1; j++) {
//                System.out.println(j);
//                newlinePos.add(j);
//            }

            switch (fullDiff.get(i).operation) {
                case EQUAL:
                    srcZeile += StringUtils.countMatches(fullDiff.get(i).text, "\n");
                    dstZeile += StringUtils.countMatches(fullDiff.get(i).text, "\n");
                    break;
                case DELETE:

                    diffInfo.setSrcStartLine(srcZeile);
                    diffInfo.setSrcStartLineOffset(srcOffset);

                    srcZeile += StringUtils.countMatches(fullDiff.get(i).text, "\n");

                    diffInfo.setSrcEndLine(srcZeile);
                    diffInfo.setSrcEndLineOffset(fullDiff.get(i).text.length() - StringUtils.countMatches(fullDiff.get(i).text, "\n"));

                    // TODO Anzahl der Zeichen hinter letztem \n = endlineOffset


                    diffInfo.setActionType("DELETE");

                    diffList.add(diffInfo);
                    break;
                case INSERT:

                    diffInfo.setDstStartLine(srcZeile);
                    diffInfo.setDstStartLineOffset(srcOffset);

                    dstZeile += StringUtils.countMatches(fullDiff.get(i).text, "\n");

                    diffInfo.setDstEndLine(dstZeile);
                    diffInfo.setDstEndLineOffset(fullDiff.get(i).text.length() - StringUtils.countMatches(fullDiff.get(i).text, "\n"));

                    diffInfo.setActionType("INSERT");

                    diffList.add(diffInfo);
                    break;
                default:
                    System.out.println("Failed to setActionType: DmpMain, Line ca. 103-113");
            }



            diffInfo.setSrcID(i);


        }


        return diffList;
    }


}
