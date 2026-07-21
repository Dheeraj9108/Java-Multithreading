package models;

import java.util.concurrent.Callable;

public class Task2 implements Callable<ChunkOutput>{

    private String str;
    private int start;
    private int end;

    public Task2(String str, int start, int end) {
        this.str = str;
        this.start = start;
        this.end = end;
    }

    @Override
    public ChunkOutput call() throws Exception {
        int opens = 0;
        int closed = 0;
        for(int i = start;i<end;i++){
            if(str.charAt(i) == '(') opens++;
            else if(opens > 0){
                opens--;
            } else {
                closed++;
            }
        }

        return new ChunkOutput(opens, closed);
    }
    
}
