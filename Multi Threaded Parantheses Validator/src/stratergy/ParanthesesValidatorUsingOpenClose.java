package stratergy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import models.ChunkOutput;
import models.Task2;

public class ParanthesesValidatorUsingOpenClose implements IParanthesesValidator {

    @Override
    public boolean validate(String str, int numThreads) {
        int strLen = str.length();
        if (strLen % 2 != 0)
            return false;

        int chunkSize = (strLen + numThreads - 1) / numThreads;
        ExecutorService es = Executors.newFixedThreadPool(numThreads);
        try {
            List<Future<ChunkOutput>> futures = new ArrayList<>();

            for (int start = 0; start < strLen; start += chunkSize) {
                int end = Math.min(start + chunkSize, strLen);
                futures.add(es.submit(new Task2(str, start, end)));
            }

            ChunkOutput prev = null;
            for (Future<ChunkOutput> future : futures) {
                try {
                    ChunkOutput cur = future.get();
                    if (prev != null) {
                        int matched = Math.min(prev.getOpens(), cur.getClosed());
                        int newOpens = prev.getOpens() + cur.getOpens() - matched;
                        int newClosed = prev.getClosed() + cur.getClosed() - matched;
                        prev = new ChunkOutput(newOpens, newClosed);
                    } else {
                        prev = cur;
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }

            return prev.getClosed() == 0 && prev.getOpens() == 0;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            es.shutdown();
        }
    }
}