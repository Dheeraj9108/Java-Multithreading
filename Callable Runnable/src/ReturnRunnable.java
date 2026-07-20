public class ReturnRunnable<T> implements Runnable, MyFuture<T>{
    private MyCallable<T> callable;
    private T result;

    public ReturnRunnable(MyCallable<T> callable){
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            this.result = callable.compute();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public T getResult(){
        return result;
    }
}
