public class App {
    public static void main(String[] args) throws Exception {
        TrafficController controller = new TrafficController();

        Thread t1 = new Thread(controller::northSignal);
        Thread t2 = new Thread(controller::eastSignal);
        Thread t3 = new Thread(controller::southSignal);
        Thread t4 = new Thread(controller::westSignal);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
