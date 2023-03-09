import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class a14_Exchanger_1 {
    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable r1 = () ->{
            System.out.println(Thread.currentThread().getName() + " #1");
            String msg = "Hello";
            System.out.println(exchangeMethod(msg));
        };
        Runnable r2 = () ->{
            System.out.println(Thread.currentThread().getName() + " #2");
            String msg = "World";
            System.out.println(exchangeMethod(msg));
        };
        executorService.execute(r1);
        executorService.execute(r2);
        executorService.shutdown();
    }

    private static String exchangeMethod(String msg) {
        try {
            String exchange = exchanger.exchange(msg);
            return exchange;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
