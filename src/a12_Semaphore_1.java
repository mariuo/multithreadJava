import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class a12_Semaphore_1 {

    private static final Semaphore SEMAPHORE = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable r1 = () ->{
            String name = Thread.currentThread().getName();
            int user = new Random().nextInt(100);
            acquireMethod();
            System.out.println("User: " + user + " # "+ name);
            sleepMethod();
            SEMAPHORE.release();
        };
        for (int i = 0; i<500; i++){
            executorService.execute(r1);
        }
        executorService.shutdown();


    }
    private static void acquireMethod(){
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void sleepMethod(){
        try {
            int temp = new Random().nextInt(6);
            temp++;
            Thread.sleep(1000 * temp);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
