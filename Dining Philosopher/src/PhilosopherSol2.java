import java.util.concurrent.Semaphore;

public class PhilosopherSol2 implements Runnable {

    private int id;
    private Object leftFork;
    private Object rightFork;
    private Semaphore semaphore;

    public PhilosopherSol2(int id, Object leftFork, Object rightFork, Semaphore semaphore) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.semaphore = semaphore;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep(1000);
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                semaphore.acquire();
                try{
                    synchronized (rightFork) {
                        synchronized (leftFork) {
                            eat();
                        }
                    }
                } finally{
                    semaphore.release();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
