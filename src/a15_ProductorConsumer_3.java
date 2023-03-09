import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class a15_ProductorConsumer_3 {
    private static final BlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {
        Runnable productor = () -> {

//            simulationProcess();
            simulationProcessSlow();
            System.out.println("Producting...");
            int num = new Random().nextInt(10000);
            try {
                QUEUE.put(num);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }


        };
        Runnable consumer = () -> {
//            simulationProcess();
            simulationProcessSlow();
            System.out.println("Consuming...");
            try {
                QUEUE.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }

        };

        a12_Window.monitore(() -> String.valueOf(QUEUE.size()));

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleWithFixedDelay(productor, 0, 10, TimeUnit.MILLISECONDS);
        executorService.scheduleWithFixedDelay(consumer, 0, 10, TimeUnit.MILLISECONDS);

    }

    private static void simulationProcess() {
        int temp = new Random().nextInt(40);
        try {
            Thread.sleep(temp);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
    private static void simulationProcessSlow() {
        int temp = new Random().nextInt(400);
        try {
            Thread.sleep(temp);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
