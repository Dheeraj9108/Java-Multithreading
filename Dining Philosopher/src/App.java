import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
        // Object forks[] = new Object[5];

        // for(int i = 0;i<5;i++){
        //     forks[i] = new Object();
        // }

        // Break Circular wait
        // for(int i = 0;i<5;i++){
        //     PhilosopherSol1 p = new PhilosopherSol1(i, forks, forks, i == 4);
        //     Thread  t = new Thread(p); 
        //     t.start();
        // }
        
        
        // Semaphore semaphore = new Semaphore(4, true);
        // // Break Hold & Wait
        // for(int i = 0;i<5;i++){
        //     PhilosopherSol2 p = new PhilosopherSol2(i, forks, forks, semaphore);
        //     Thread  t = new Thread(p);
        //     t.start();
        // }
        
        Fork forks[] = new Fork[5];
        
        for(int i = 0;i<5;i++){
            forks[i] = new Fork(i);
        }
        
        for(int i = 0;i<5;i++){
            PhilosopherSol3 p = new PhilosopherSol3(i, forks[i], forks[(i+1)%5]);
            Thread  t = new Thread(p);
            t.start();
        }

    }
}
