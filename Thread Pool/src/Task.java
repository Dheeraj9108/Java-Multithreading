public class Task implements Runnable{

    private int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.printf("%s task is executed by %s \n", taskId, Thread.currentThread().getName());
    }
    
}
