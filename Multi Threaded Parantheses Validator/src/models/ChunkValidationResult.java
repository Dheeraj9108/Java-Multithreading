package models;
public class ChunkValidationResult {
    private int net;
    private int min;
    public ChunkValidationResult(int net, int min) {
        this.net = net;
        this.min = min;
    }
    public int getNet() {
        return net;
    }
    public int getMin() {
        return min;
    }
}
