package at.aau;

public class MDiff {


    private final String actionType;
    private final int srcID;
    private final int srcStartLine;
    private final int srcStartLineOffset;
    private final int srcEndLine;
    private final int srcEndLineOffset;

    private final int dstID;
    private final int dstStartLine;
    private final int dstStartLineOffset;
    private final int dstEndLine;
    private final int dstEndLineOffset;

    public MDiff(String actionType, int srcID, int srcStartLine, int srcStartLineOffset, int srcEndLine, int srcEndLineOffset, int dstID, int dstStartLine, int dstStartLineOffset, int dstEndLine, int dstEndLineOffset, int endLineOffset) {
        this.actionType =actionType;
        this.srcID =srcID;
        this.srcStartLine =srcStartLine;
        this.srcStartLineOffset =srcStartLineOffset;
        this.srcEndLine =srcEndLine;
        this.srcEndLineOffset =srcEndLineOffset;

        this.dstID =dstID;
        this.dstStartLine =dstStartLine;
        this.dstStartLineOffset =dstStartLineOffset;
        this.dstEndLine =dstEndLine;
        this.dstEndLineOffset =dstEndLineOffset;
    }

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



}
