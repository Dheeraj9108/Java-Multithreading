import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
        Semaphore aSemephore = new Semaphore(1);
        Semaphore bSemephore = new Semaphore(0);
        Semaphore cSemephore = new Semaphore(0);

        Thread a = new Thread(()->{
            for(int i = 0;i<2;i++){
                try {
                    aSemephore.acquire();
                    System.out.println("A");
                    bSemephore.release();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });

        Thread b = new Thread(()->{
            for(int i = 0;i<2;i++){
                try {
                    bSemephore.acquire();
                    System.out.println("B");
                    cSemephore.release();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
        Thread c = new Thread(()->{
            for(int i = 0;i<2;i++){
                try {
                    cSemephore.acquire();
                    System.out.println("C");
                    aSemephore.release();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });

        a.start();
        b.start();
        c.start();
    }
}
