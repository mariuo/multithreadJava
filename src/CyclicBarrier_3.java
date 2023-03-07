import java.util.concurrent.*;

public class CyclicBarrier_3 {

    private static BlockingQueue<Double> result = new LinkedBlockingQueue<>();

    private static ExecutorService executorService;
    private static Runnable r1;
    private static Runnable r2;
    private static Runnable r3;

    public static void main(String[] args) {
        Runnable r4 = () ->{
            System.out.println("Somando tudo.");
            double resultFinal = 0;
            resultFinal += result.poll();
            resultFinal += result.poll();
            resultFinal += result.poll();
            System.out.println("Process end. :" + resultFinal);
            System.out.println("-------------");
            restart();
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, r4);
        executorService = Executors.newFixedThreadPool(3);


        r1 = () ->{
            System.out.println("Started " + Thread.currentThread().getName());
            result.add(432d*3d);
            extracted(cyclicBarrier);
            System.out.println("Finished");
        };
        r2 = () ->{
            System.out.println("Started " + Thread.currentThread().getName());
            result.add(Math.pow(3d, 14d));
            extracted(cyclicBarrier);
            System.out.println("Finished");
        };
        r3 = () ->{
            System.out.println("Started " + Thread.currentThread().getName());
            result.add(45d*127d/12d);
            extracted(cyclicBarrier);
            System.out.println("Finished");
        };
        restart();

//        executorService.shutdown();
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
    private static void restart(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r3);
    }


}
