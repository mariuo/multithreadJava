import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class a14_SynchronousQueue_1 {
    private static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable r1 = () ->{
            putMethod();
            System.out.println("Writed");
        };
        Runnable r2 = () ->{
            System.out.println("Get msg: "+takeMethod());
        };
        executorService.execute(r1);
        executorService.execute(r2);
        executorService.shutdown();
    }
    private static void putMethod(){
        try{
            QUEUE.put("Queue sync");

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
    private static String takeMethod(){
        try {
            String msg = QUEUE.take();
            return msg;
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return null;
        }

    }
}
