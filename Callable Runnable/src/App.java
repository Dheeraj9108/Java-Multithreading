public class App {
    public static void main(String[] args) throws Exception {
        MyCallable<Integer> callable = ()->{ return 10; };

        ReturnRunnable<Integer> runnable = new ReturnRunnable<>(callable);

        Thread t = new Thread(runnable);

        t.start();
        t.join();

        System.out.println(runnable.getResult());
    }
}
