import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class a15_ProductorConsumer_2 {
    private static final BlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<>(5);
    private static volatile boolean isProducting = true;
    private static volatile boolean isConsuming = true;
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Thread productor = new Thread(() -> {
            while (true) {
                try {
                    simulationProcess();
                    if (isProducting) {
                        LOCK.lock();
                        System.out.println("Producting...");
                        int num = new Random().nextInt(10000);
                        QUEUE.add(num);
//                        System.out.println("Added...: " + num);

                        if (QUEUE.size() == 5) {
                            System.out.println("Pause - Productor...");
                            isProducting = false;
                        }
                        if (QUEUE.size() == 1) {
                            System.out.println("Starting - Consumer...");
                            isConsuming = true;
                        }
                        LOCK.unlock();
                    } else {
                        System.out.println("!!! Productor sleeping...!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        });
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    simulationProcess();
                    if (isConsuming) {
                        LOCK.lock();
                        System.out.println("Consuming...");
                        Optional<Integer> num = QUEUE.stream().findFirst();
                        num.ifPresent(n -> {
                            QUEUE.remove(n);
                        });
//                        System.out.println("Removed...: " + num.get());
                        if (QUEUE.size() == 0) {
                            System.out.println("Pause - Consumer...");
                            isConsuming = false;
                        }
                        if (QUEUE.size() == 4) {
                            System.out.println("Starting - Productor...");
                            isProducting = true;
                        }
                        LOCK.unlock();
                    } else {
                        System.out.println("??? Consumer sleeping...!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        a12_Window.monitore(() -> String.valueOf(QUEUE.size()));

        productor.start();
        consumer.start();
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
}
