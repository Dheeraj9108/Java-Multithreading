package models;
import java.util.concurrent.Callable;

public class Task1 implements Callable<ChunkValidationResult>{

    private String str;
    private int start;
    private int end;

    public Task1(String str, int start, int end) {
        this.str = str;
        this.start = start;
        this.end = end;
    }

    @Override
    public ChunkValidationResult call() throws Exception {
        int balance = 0;
        int min = 0;
        for(int i = start;i<end;i++){
            if(str.charAt(i) == '(') balance++;
            else balance--;

            min = Math.min(min,balance);
        }

        return new ChunkValidationResult(balance, min);
    }
}
