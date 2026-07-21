package models;

public class ChunkOutput {
    private int opens;
    private int closed;

    public ChunkOutput(int opens, int closed) {
        this.opens = opens;
        this.closed = closed;
    }

    public int getOpens() {
        return opens;
    }

    public int getClosed() {
        return closed;
    }
}
