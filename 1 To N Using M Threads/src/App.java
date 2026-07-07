class Shared{
    private int total_threads = 3;
    private int counter = 0;

    Shared(int total_threads){
        this.total_threads = total_threads;
    }

    public int getCounter() {
        return this.counter;
    }

    public int getTotalThreads(){
        return this.total_threads;
    }

    public void increment(){
        counter++;
    }
}

class Printer implements Runnable {
    private Shared shared;
    private int rem; 

    Printer(Shared shared, int rem){
        this.shared = shared;
        this.rem = rem;
    }

    private void print(int val){
        System.out.printf("[%s] : %s \n", Thread.currentThread().getName(), val);
    }

    @Override
    public void run() {
        for(int i = 1; i<=10; i++){
            synchronized(shared){
                try {
                    while(shared.getCounter()%shared.getTotalThreads() != rem){
                        shared.wait();
                    } 
                    print(shared.getCounter());
                    shared.increment();
                    shared.notifyAll();
                } catch (Exception e) {
                    Thread.interrupted();
                }
            }
        }
    }


}

public class App {
    public static void main(String[] args) throws Exception {
        Shared shared = new Shared(3);
        for(int i = 0;i<3;i++){
            Printer printer = new Printer(shared, i);
            Thread thread = new Thread(printer, String.format("%s THREAD", i));
            thread.start();
        }
    }
}
