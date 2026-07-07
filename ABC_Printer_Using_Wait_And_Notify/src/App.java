class Shared {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increment(){
        counter++;
    }
}


class Printer implements Runnable{

    private Shared shared;
    private int rem;
    private char ch;

    Printer(Shared shared, int rem, char ch){
        this.shared = shared;
        this.rem = rem;
        this.ch = ch;
    }

    @Override
    public void run() {
        for(int i = 0;i<2;i++){
            synchronized(shared){
                try {
                    while(shared.getCounter()%3 != rem){
                        shared.wait();
                    }
                    System.err.printf("[%s THREAD] : %s \n", Thread.currentThread().getName(), ch);
                    shared.increment();
                    shared.notifyAll();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        Shared shared = new Shared();
        Printer aprinter = new Printer(shared, 0, 'A');
        Printer bprinter = new Printer(shared, 1, 'B');
        Printer cprinter = new Printer(shared, 2, 'C');
        Thread athread = new Thread(aprinter, "A");
        Thread bthread = new Thread(bprinter, "B");
        Thread cthread = new Thread(cprinter, "C");
        athread.start();
        bthread.start();
        cthread.start();
    }
}
