import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private int id;
    ReentrantLock lock = new ReentrantLock();

    public Fork(int id){
        this.id = id;
    }

    public boolean tryLock(int philosopherId, int waitTimeInSeconds) throws InterruptedException{
        boolean acquired = lock.tryLock(waitTimeInSeconds, TimeUnit.SECONDS);

        if(acquired){
            System.out.printf("%s acquired fork %s \n", philosopherId, id);
        }

        return acquired;
    }

    public void unlock(int philosopherId){
        lock.unlock();
        System.out.printf("%s released fork %s",philosopherId, id);
    }
}
