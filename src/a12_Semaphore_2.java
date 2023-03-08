import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class a12_Semaphore_2 {

    private static final Semaphore SEMAPHORE = new Semaphore(15);
    private static AtomicInteger QTD = new AtomicInteger(0);

    public static void main(String[] args) {
        ScheduledExecutorService executorService =
                Executors.newScheduledThreadPool(501);

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            int user = new Random().nextInt(100);

            boolean result = false;
            QTD.incrementAndGet();
            while (!result) {
                result = tryAcquireMethod();
            }
            QTD.decrementAndGet();

            System.out.println("User: " + user + " # " + name);
            sleepMethod();
            SEMAPHORE.release();
        };

        a12_Window.Message window = a12_Window.createWindow("QTD");
        Runnable r2 = () -> {
            int qtd = QTD.get();
            window.setText(qtd + " users waiting.");
        };

        for (int i = 0; i < 500; i++) {
            executorService.execute(r1);
        }
        executorService.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);


    }

    private static boolean tryAcquireMethod() {
        try {
            return SEMAPHORE.tryAcquire(1, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return false;
        }
    }

    private static void sleepMethod() {
        try {
            int temp = new Random().nextInt(6);
            temp++;
            Thread.sleep(1000 * temp);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
