import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Task1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Executing Task 1" + Thread.currentThread().getName());
        Thread.sleep(3000);
        return 30;
    }
}

class Task2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Executing Task 2" + Thread.currentThread().getName());
        Thread.sleep(1000);
        return 50;
    }
}

class Task3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Executing Task 2" + Thread.currentThread().getName());
        Thread.sleep(1000);
        throw new RuntimeException("Some thing went wrong in Task 3");
    }
}

public class CallableMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        Future<Integer> future1 = es.submit(new Task1());
        Future<Integer> future2 = es.submit(new Task2());
        Future<Integer> future3 = es.submit(new Task3());

        try {
            int result1 = future1.get(2, TimeUnit.SECONDS);
            System.out.println("Task 1 result " + result1);
            
            int result2 = future2.get();
            System.out.println("Task 2 result " + result2);
            
            int result3 = future3.get();
            System.out.println("Task 3 result " + result3);

        } catch(TimeoutException e){
            System.out.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getCause().getMessage());
        }

        es.shutdown();

        // isDone() - returns true if the task is finished in any way (exception / success)
        // cancel(boolean mayInterrutRunningThread) - attempts to cancel the task
        // isCancelled() - returns true if the task was cancelled.
    }
}
