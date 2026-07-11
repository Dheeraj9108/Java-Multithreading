// import java.util.concurrent.ArrayBlockingQueue;
// import java.util.concurrent.BlockingQueue;

// class ThreadPool{

//     private BlockingQueue<Integer> pool = new ArrayBlockingQueue<>(5);

//     public void produce(int val){
//         try {
//             pool.put(val);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }

//     public int consume(){
//         try {
//             return pool.take();
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//         return 0;
//     }
// }


// class ProduceTask implements Runnable{

//     private ThreadPool pool;

//     public ProduceTask(ThreadPool pool){
//         this.pool = pool;
//     }

//     @Override
//     public void run() {
//         int i = 0;
//         while(i< 20){
//             System.out.printf("[%s] produced %s \n", Thread.currentThread().getName(), i);
//             pool.produce(i);
//             i++;
//              try {
//                 Thread.sleep(500);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

// } 

// class ConsumeTask implements Runnable{

//     private ThreadPool pool;

//     public ConsumeTask(ThreadPool pool){
//         this.pool = pool;
//     }

//     @Override
//     public void run() {
//         int i = 0;
//         while(i< 10){
//             int val = pool.consume();
//             System.out.printf("[%s] consumed %s \n", Thread.currentThread().getName(), val);
//             i++;
//             try {
//                 Thread.sleep(500);
//             } catch (InterruptedException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         }
//     }

// } 

public class App {
    public static void main(String[] args) throws Exception {

        // ThreadPool pool = new ThreadPool();
        // ProduceTask produceTask = new ProduceTask(pool);

        // Thread producer = new Thread(produceTask, "Producer");

        // ConsumeTask ctask = new ConsumeTask(pool);
        // Thread consumer1 = new Thread(ctask, "Consumer 1");
        // Thread consumer2 = new Thread(ctask, "Consumer 2");

        // producer.start();
        // consumer1.start();
        // consumer2.start();

        ThreadPool pool = new ThreadPool(3);
        for(int i = 0;i<10;i++){
            Task task = new Task(i);
            pool.submit(task);
        }
    }
}
