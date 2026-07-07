import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
       Semaphore pingSemaphore = new Semaphore(1);
       Semaphore pongSemaphore = new Semaphore(0);

       Thread pingThread = new Thread(()->{
        for(int i = 0;i<10;i++) {
            try {
                pingSemaphore.acquire();
                System.out.println("PING");
                pongSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       });

       Thread pongThread = new Thread(()->{
        for (int i = 0;i<10;i++) {
            try {
                pongSemaphore.acquire();
                System.out.println("PONG");
                pingSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       });

       pingThread.start();
       pongThread.start();
    }
}
