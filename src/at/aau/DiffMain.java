package at.aau;

import at.aau.DiffInfo;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DiffMain {
    public static List<DiffInfo> diffFiles(String srcFilename, String dstFilename) {

        List<String> original = fileToLines(srcFilename);
        List<String> revised  = fileToLines(dstFilename);

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff(original, revised);


        ArrayList<DiffInfo> diffList = new ArrayList<>();

        int i = 0;


        for (Object o : patch.getDeltas()) {
            DiffInfo diffInfo = new DiffInfo();
            Delta delta = (Delta) o;
            System.out.println(delta.toString());


            if (delta.getType().equals(Delta.TYPE.CHANGE)) {
                diffInfo.setActionType("UPDATE");
            } else if (delta.getType().equals(Delta.TYPE.INSERT)) {
                diffInfo.setActionType("INSERT");
            } else if (delta.getType().equals(Delta.TYPE.DELETE)) {
                diffInfo.setActionType("DELETE");
            } else {
                diffInfo.setActionType("UNKNOWN");
            }


            diffInfo.setSrcID(i);
            diffInfo.setSrcStartLine(delta.getOriginal().getPosition());
            diffInfo.setSrcEndLine(delta.getOriginal().size() - 1 + delta.getOriginal().getPosition());


            diffInfo.setDstID(i);
            diffInfo.setDstStartLine(delta.getRevised().getPosition());

            List originalLines = delta.getOriginal().getLines();
            if (!originalLines.isEmpty()) {
                diffInfo.setSrcEndLineOffset(originalLines.get(originalLines.size() - 1).toString().length());
            }

            List revisedLines = delta.getRevised().getLines();
            if (!revisedLines.isEmpty()) {
                diffInfo.setDstEndLineOffset((revisedLines.get(revisedLines.size() - 1).toString().length()));
            }


            if (diffInfo.getActionType().equals("DELETE")) {
                diffInfo.setDstEndLine(delta.getRevised().size() + delta.getRevised().getPosition());
            } else {
                diffInfo.setDstEndLine(delta.getRevised().size() - 1 + delta.getRevised().getPosition());
            }



            diffList.add(diffInfo);

            System.out.println("ActionType: " + diffInfo.getActionType());

            System.out.println("srcID: " + diffInfo.getSrcID());
            System.out.println("srcStartLine: " + diffInfo.getSrcStartLine());
            System.out.println("srcEndline: " + diffInfo.getSrcEndLine());
            System.out.println("srcEndlineOffset: " + diffInfo.getSrcEndLineOffset());

            System.out.println("dstStartLine: " + diffInfo.getDstStartLine());
            System.out.println("dstEndline: " + diffInfo.getDstEndLine());
            System.out.println("dstEndlineOffset: " + diffInfo.getDstEndLineOffset());
            System.out.println("dstID: " + diffInfo.getDstID());

            i++;
        }
        return diffList;
    }

    public static List<String> fileToLines(String fileName) {
        ArrayList<String> liste = new ArrayList<>();

        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(liste::add);
        }  catch (IOException ex) {
            System.out.println("Something doesn't work!");
        }
        return liste;
    }


}
