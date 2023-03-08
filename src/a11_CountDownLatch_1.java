import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class a11_CountDownLatch_1 {
    //Garants
    private static volatile int i = 0;
    private static CountDownLatch latch = new CountDownLatch(3); // Not reutilisable
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

        Runnable r1 = () -> {
            int j = new Random().nextInt(1000);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            latch.countDown();
        };
        Runnable r2 = () ->{
            awaitMethod();
            i = new Random().nextInt(1000);

        };
        Runnable r3 = () ->{
            awaitMethod();
            latch = new CountDownLatch(3); //

        };
        Runnable r4 = () ->{
            awaitMethod();
            System.out.println("Ended.");

        };
        executorService.scheduleAtFixedRate(r1, 0 ,1, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(r2, 0 ,1, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(r3, 0 ,1, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(r4, 0 ,1, TimeUnit.SECONDS);


        while(true){
            awaitMethod();
            i = new Random().nextInt(100);
            latch = new CountDownLatch(3); //
        }
    }
    private static void sleepMethod(){
                    try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
    private static void awaitMethod(){
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
