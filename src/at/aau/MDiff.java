package at.aau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static at.aau.DiffMain.diffFiles;

public class MDiff {

    private String returnString;

    private String actionType;
    private int srcID;
    private int srcStartLine;
    private int srcStartLineOffset;
    private int srcEndLine;
    private int srcEndLineOffset;

    private int dstID;
    private int dstStartLine;
    private int dstStartLineOffset;
    private int dstEndLine;
    private int dstEndLineOffset;
    private int i = 0;
//    public MDiff(String actionType, int srcID, int srcStartLine, int srcStartLineOffset, int srcEndLine, int srcEndLineOffset, int dstID, int dstStartLine, int dstStartLineOffset, int dstEndLine, int dstEndLineOffset, int endLineOffset) {
//        List<DiffInfo> myList = diffFiles("Textfiles/originalFile.txt", "TextFiles/revisedFile.txt");
//        int j = myList.size()-1;
//
//        this.actionType =actionType;
//        this.srcID =srcID;
//        this.srcStartLine =srcStartLine;
//        this.srcStartLineOffset =srcStartLineOffset;
//        this.srcEndLine =srcEndLine;
//        this.srcEndLineOffset =srcEndLineOffset;
//
//        this.dstID =dstID;
//        this.dstStartLine =dstStartLine;
//        this.dstStartLineOffset =dstStartLineOffset;
//        this.dstEndLine =dstEndLine;
//        this.dstEndLineOffset =dstEndLineOffset;
//
//
//        for(int i = 0; i< j; i++) {
//               actionType = myList.get(i).getActionType();
//               srcID = myList.get(i).getSrcID();
//               srcStartLine = myList.get(i).getSrcStartLine();
//               srcStartLineOffset = myList.get(i).getSrcStartLineOffset();
//               srcEndLine = myList.get(i).getSrcEndLine();
//               srcEndLineOffset = myList.get(i).getSrcEndLineOffset();
//
//               dstID = myList.get(i).getDstID();
//               dstStartLine = myList.get(i).getDstStartLine();
//               dstStartLineOffset = myList.get(i).getDstStartLineOffset();
//               dstEndLine = myList.get(i).getDstEndLine();
//               dstEndLineOffset = myList.get(i).getDstEndLineOffset();
//           }
//    }

    public MDiff(String f1, String f2) {
        List<DiffInfo> diffInfoList = diffFiles(f1, f2);

        for (DiffInfo diffInfo : diffInfoList) {
            returnString += diffInfoList.get(i).getActionType();

            returnString += diffInfoList.get(i).getSrcID();
            returnString += diffInfoList.get(i).getSrcStartLine();
            returnString += diffInfoList.get(i).getSrcStartLineOffset();
            returnString += diffInfoList.get(i).getSrcEndLine();
            returnString += diffInfoList.get(i).getSrcEndLineOffset();

            returnString += diffInfoList.get(i).getDstID();
            returnString += diffInfoList.get(i).getDstStartLine();
            returnString += diffInfoList.get(i).getDstStartLineOffset();
            returnString += diffInfoList.get(i).getDstEndLine();
            returnString += diffInfoList.get(i).getDstEndLineOffset();
            i++;
        }

    }

//        Iterator<DiffInfo> iterator = diffInfoList.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//
//        for (int i = 1; i < diffInfoList.size(); i++) {
//            returnString += diffInfoList.get(i).getActionType();
//        }
//    }

//    private void addToString(String f1, String f2) {
//        returnString += actionType;
//
//    }


    public String getActionType() {
        return actionType;
    }

    public int getSrcID() {
        return srcID;
    }

    public int getSrcStartLine() {
        return srcStartLine;
    }

    public int getSrcStartLineOffset() {
        return srcStartLineOffset;
    }

    public int getSrcEndLine() {
        return srcEndLine;
    }

    public int getSrcEndLineOffset() {
        return srcEndLineOffset;
    }

    public int getDstID() {
        return dstID;
    }

    public int getDstStartLine() {
        return dstStartLine;
    }

    public int getDstStartLineOffset() {
        return dstStartLineOffset;
    }

    public int getDstEndLine() {
        return dstEndLine;
    }

    public int getDstEndLineOffset() {
        return dstEndLineOffset;
    }

    public String getReturnString() {
        return returnString;
    }
}
