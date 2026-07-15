public class PhilosopherSol1 implements Runnable{

    private int id;
    private Object leftFork;
    private Object rightFork;
    private boolean isLast;
    public PhilosopherSol1(int id, Object leftFork, Object rightFork, boolean isLast) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.isLast = isLast;
    }

    private void think() throws InterruptedException{
        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException{
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep(1000);
    }

    @Override
    public void run() {
        try {
            while(true){
                think();

                if(isLast){
                    synchronized(rightFork){
                        synchronized(leftFork){
                            eat();
                        }
                    }
                } else {
                    synchronized(leftFork){
                        synchronized(rightFork){
                            eat();
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    
}
