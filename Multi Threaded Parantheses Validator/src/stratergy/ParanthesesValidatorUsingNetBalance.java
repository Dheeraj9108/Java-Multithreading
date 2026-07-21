package stratergy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import models.ChunkValidationResult;
import models.Task1;

public class ParanthesesValidatorUsingNetBalance implements IParanthesesValidator {

    @Override
    public boolean validate(String str, int numThreads) {
        int strLen = str.length();
        int chunkSize = (strLen + numThreads - 1) / numThreads;
        if (strLen % 2 != 0)
            return false;
        ExecutorService es = Executors.newFixedThreadPool(numThreads);
        try {
            List<Future<ChunkValidationResult>> futures = new ArrayList<>();

            for (int start = 0; start < strLen; start += chunkSize) {
                int end = Math.min(start + chunkSize, strLen);
                futures.add(es.submit(new Task1(str, start, end)));
            }

            int runningBalance = 0;
            for (Future<ChunkValidationResult> future : futures) {
                try {
                    ChunkValidationResult res = future.get();

                    if (runningBalance + res.getMin() < 0) {
                        return false;
                    }
                    runningBalance += res.getNet();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }

            return runningBalance == 0;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            es.shutdown();
        }
    }
}