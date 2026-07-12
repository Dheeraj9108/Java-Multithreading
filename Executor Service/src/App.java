import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Fixed Thread Pool

        // ExecutorService es = Executors.newFixedThreadPool(3);

        // for(int i = 0;i<10;i++){
        //     final int taskId = i;
        //     es.submit(()->{
        //         System.out.printf("[%s] executed %s \n", Thread.currentThread().getName(), taskId);

        //         try {
        //             Thread.sleep(500);
        //         } catch (Exception e) {
        //             Thread.currentThread().interrupt();
        //         }
        //     });
        // }

        // Cached Thread Pool

        // ExecutorService es = Executors.newCachedThreadPool();

        // for(int i = 0;i<10;i++){
        //     final int taskId = i;
        //     es.submit(()->{
        //         System.out.printf("[%s] executed %s \n", Thread.currentThread().getName(), taskId);
        //         try {
        //             Thread.sleep(500);
        //         } catch (Exception e) {
        //             Thread.currentThread().interrupt();
        //         }
        //     });
        // }

        // Thread.sleep(3000);

        // for(int i = 0;i<10;i++){
        //     final int taskId = i;
        //     es.submit(()->{
        //         System.out.printf("[%s] executed %s \n", Thread.currentThread().getName(), taskId);
        //         try {
        //             Thread.sleep(500);
        //         } catch (Exception e) {
        //             Thread.currentThread().interrupt();
        //         }
        //     });
        // }


        // Single Thread Pool

        // ExecutorService es = Executors.newSingleThreadExecutor();

        // for(int i = 0;i<10;i++){
        //     final int taskId = i;
        //     es.submit(()->{
        //         System.out.printf("[%s] executed %s \n", Thread.currentThread().getName(), taskId);
        //         try {
        //             Thread.sleep(500);
        //         } catch (Exception e) {
        //             Thread.currentThread().interrupt();
        //         }
        //     });
        // }

        
        // Scheduled Executor

        // ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        
        // Execute a task once after 5s

        // es.schedule(()->{
        //     System.out.println("Task executed after 5s");
        // }, 5, TimeUnit.SECONDS);

        
        // Execute tasks at fixed rate

        // es.scheduleAtFixedRate(()->{
        //     System.out.println("Task execution started");
        //     try {
        //         Thread.sleep(3000);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        //     System.out.println("Task execution completed");
            
        // }, 0, 5, TimeUnit.SECONDS);
        
        // Execute tasks at fixed delay

        // es.scheduleWithFixedDelay(()->{
        //     System.out.println("Task Execution Started");
        //     try {
        //         Thread.sleep(2000);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        //     System.out.println("Task Execution Completed");
        // }, 0, 2, TimeUnit.SECONDS);

        // es.shutdown();

        // Work stealing pool - creates a pool with parallelism level usually equal to number of available processors

        ExecutorService es = Executors.newWorkStealingPool();

        for(int i = 0;i<20;i++){
            es.submit(()->{
                System.out.printf("%s \n", Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            });
        }

        es.awaitTermination(2, TimeUnit.SECONDS);

        es.shutdown();
    }
}


// Core Pool Size : Pool attempts to keep this many threads alive at all the times. even if they are idle. New tasks are assigned to these threads first.

// Maximum Pool Size : If the queue is full the pool creates new threads up to this limit. (Max number of threads that the pool can create if all the existing threads in the pool are not in idle state)

// Keep Alive Time : Maximum time that the excess threads will wait for  a new taks before terminating.


// Fixed Thread Pool | Single Thread Pool : LinkedBlockingQueue : Queue accepts tasks indefinitely if threads are busy

// Cache Thread Pool : Sychronous Queue : Zero capacity queue only accepts the task if the thread is waiting or ready to accept the task (or create new thread for the new task)

// Scheduled Thread Pool : DelayedWorkQueue : orders task based on their scheduled execution time.

// ArrayBlockingQueue : Queue with fixed capacity
