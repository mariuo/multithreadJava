import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;

public class a09_Executors_Scheduled {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

//        ScheduledFuture<String> future =  executorService.schedule(new Job(), 2, TimeUnit.SECONDS);
//       executorService.schedule(new Job(), 2, TimeUnit.SECONDS);
//        System.out.println(future.get());

//        executorService.scheduleAtFixedRate(new Job(), 0, 1, TimeUnit.SECONDS); // Execute the job in 1s, if the job delays more the 1s, dont wait. If less then 1s, waits and 1s and then execute.
        executorService.scheduleWithFixedDelay(new Job(), 0, 1, TimeUnit.SECONDS); // Always have a delay between the jobs.
//        executorService.shutdown();

    }
    public static class Job implements Runnable {
        @Override
        public void run() {
            try {
//                Thread.sleep(500);
                Thread.sleep(2000); // With FixedDelay - will execute 2s + 1s. = 3s.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(LocalTime.now());
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            System.out.println(name + " : " + "My job" + " : " + nextInt);
        }
    }
//    public static class Job implements Callable<String> {
//        @Override
//        public String call() throws Exception {
//            String name = Thread.currentThread().getName();
//            int nextInt = new Random().nextInt(1000);
//            return name + " : " + "My job" + " : " + nextInt;
//        }
//    }
}
