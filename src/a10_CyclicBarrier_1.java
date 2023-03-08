import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a10_CyclicBarrier_1 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable r1 = () ->{
            System.out.println(432d*3d);
            extracted(cyclicBarrier);
            System.out.println("Finished");
        };
        Runnable r2 = () ->{
            System.out.println(Math.pow(3d, 14d));
            extracted(cyclicBarrier);
            System.out.println("Finished");
        };
        Runnable r3 = () ->{
            System.out.println(45d*127d/12d);
            extracted(cyclicBarrier);
            System.out.println("Finished");
        };
        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r3);

        executorService.shutdown();



    }

    private static void extracted(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

}
