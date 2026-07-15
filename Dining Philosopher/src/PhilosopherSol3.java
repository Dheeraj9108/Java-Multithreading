public class PhilosopherSol3 implements Runnable{

    private int id;
    private Fork leftFork;
    private Fork rightFork;

    public PhilosopherSol3(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException{
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep((int)(Math.random() * 1000));
    }
    
    private void eat() throws InterruptedException{
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep((int)(Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while(true){
                think();
                boolean eat = false;
                while(!eat){
                    boolean gotLeftFork = leftFork.tryLock(id, 1);
                    if(gotLeftFork){
                        boolean gotRightFork = rightFork.tryLock(id, 1);
                        if(gotRightFork){
                            System.out.printf("%s aquired both fork", id);
                            eat();
                            eat = true;
                        } else {
                            System.out.printf("%s released left fork", id);
                            leftFork.unlock(id);
                            Thread.sleep((int)(Math.random()*100));
                        }
                    } else {
                        Thread.sleep((int)(Math.random()*100));
                    }  
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    
}
