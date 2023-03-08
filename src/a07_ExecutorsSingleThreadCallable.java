import java.util.Random;
import java.util.concurrent.*;

public class a07_ExecutorsSingleThreadCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService exe = null;
        try{
            exe = Executors.newSingleThreadExecutor();
            Future<String> future = exe.submit(new Job());
            System.out.println(future.isDone());
//            System.out.println(future.get());
            System.out.println(future.get(2, TimeUnit.SECONDS));
            System.out.println(future.isDone());
//            exe.shutdown();
//            exe.awaitTermination(10, TimeUnit.SECONDS);
//
//            System.out.println(future.isDone());

        }catch(Exception e){
            throw e;
        }finally {
            if(exe != null){
                exe.shutdownNow();

            }

        }

    }
    public static class Job implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            return name + " : " + "My job" + " : " + nextInt;
        }
    }
}
