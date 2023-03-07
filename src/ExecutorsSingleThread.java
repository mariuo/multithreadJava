import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorsSingleThread {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = null;
        try{
            exe = Executors.newSingleThreadExecutor();
            exe.execute(new Job());
            exe.execute(new Job());
            exe.execute(new Job());
            Future<?> future = exe.submit(new Job());
            boolean done = future.isDone();
            System.out.println(done);
            exe.shutdown();
            exe.awaitTermination(10, TimeUnit.SECONDS);

            done = future.isDone();
            System.out.println(done);

        }catch(Exception e){
            throw e;
        }finally {
            if(exe != null){
                exe.shutdownNow();

            }

        }

    }
    public static class Job implements Runnable{
        @Override
        public void run(){
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + "My job");
        }
    }
}
