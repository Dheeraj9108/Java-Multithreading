import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficController{
    ReentrantLock lock = new ReentrantLock();
    Condition northCondition = lock.newCondition();
    Condition southCondition = lock.newCondition();
    Condition eastCondition = lock.newCondition();
    Condition westCondition = lock.newCondition();
    private Direction direction = Direction.NORTH;

    public TrafficController(){

    }

    public void northSignal(){
        for(int i = 0;i<3;i++){
            lock.lock();
            try {
                while(direction != Direction.NORTH){
                    northCondition.await();
                }
                System.out.println("North signal is Green");
                direction = Direction.EAST;
                eastCondition.signal();
            } catch (Exception e) {
                // TODO: handle exception
            } finally{
                lock.unlock();
            }
        }    
    }
    
    public void eastSignal(){
        for(int i = 0;i<3;i++){
            lock.lock();
            try {
                while(direction != Direction.EAST){
                    eastCondition.await();
                }
                System.out.println("East signal is Green");
                direction = Direction.SOUTH;
                southCondition.signal();
            } catch (Exception e) {
                // TODO: handle exception
            } finally{
                lock.unlock();
            }
        }    
    }
    public void southSignal(){
        for(int i = 0;i<3;i++){
            lock.lock();
            try {
                while(direction != Direction.SOUTH){
                    southCondition.await();
                }
                System.out.println("South signal is Green");
                direction = Direction.WEST;
                westCondition.signal();
            } catch (Exception e) {
                // TODO: handle exception
            } finally{
                lock.unlock();
            }
        }    
    }
    public void westSignal(){
        for(int i = 0;i<3;i++){
            lock.lock();
            try {
                while(direction != Direction.WEST){
                    westCondition.await();
                }
                System.out.println("West signal is Green");
                direction = Direction.NORTH;
                northCondition.signal();
            } catch (Exception e) {
                // TODO: handle exception
            } finally{
                lock.unlock();
            }
        }    
    }
}