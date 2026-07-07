class Printer implements Runnable{
    private static int counter;
    private int rem;
    private static Object lock;

    public Printer(int rem){
        this.counter = 0;
        this.lock = new Object();
        this.rem = rem; 
    }

    private void print(int val){
        System.out.printf("[%s] : %s \n", Thread.currentThread().getName(), val);
    } 

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            synchronized(lock){
                try {
                    if(counter%2 == rem){
                        print(counter);
                        counter++;
                    } else {
                        lock.wait();
                    }    
                    lock.notifyAll();
                } catch (Exception e) {
                    Thread.interrupted();
                }
            }
        }
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        Printer odd = new Printer(1);
        Printer even = new Printer(0);

        Thread oddThread = new Thread(odd, "ODD THREAD");
        Thread evenThread = new Thread(even, "EVEN THREAD");

        oddThread.start();
        evenThread.start();
    }
}
