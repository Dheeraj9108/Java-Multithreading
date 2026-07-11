import java.util.concurrent.BlockingQueue;

public class Worker extends Thread{

    private BlockingQueue<Task> queue;
    
    public Worker(BlockingQueue<Task> queue, String name){
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = queue.take();
                task.run();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
}
