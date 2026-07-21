import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(5);
        SharedResource resource = new SharedResource();
        for (int i = 0; i < 4; i++) {
            es.submit(() -> {
                while (true) {
                    resource.read();
                }
            });

        }

        es.submit(() -> {
            while (true) {
                resource.write(1);
            }
        });

        es.shutdown();

    }
}
