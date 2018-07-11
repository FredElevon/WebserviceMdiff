package at.aau;

public class MDiff {
    private final long id;
    private final String content;

    public MDiff(long id, String content) {
        this.id =id;
        this.content =content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
