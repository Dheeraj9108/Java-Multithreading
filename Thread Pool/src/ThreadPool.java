import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool {

    private int poolSize;
    private List<Worker> workers;
    private BlockingQueue<Task> queue;

    public ThreadPool(int poolSize){
        this.poolSize = poolSize;
        workers = new ArrayList<>();
        queue = new LinkedBlockingDeque<>();

        for(int i = 0;i<poolSize;i++){
            Worker worker = new Worker(queue, "Worker "+i);
            worker.start();
            workers.add(worker);
        }
    }

    public void submit(Task task){
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        for(Worker worker : workers){
            worker.interrupt();
        }
    }
}
