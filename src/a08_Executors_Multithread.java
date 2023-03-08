import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class a08_Executors_Multithread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = null;
        try{
//            executorService = Executors.newFixedThreadPool(3);
            executorService = Executors.newCachedThreadPool(); // WHen you need: Create new thread.
            List<Job> lista = new ArrayList<>();
            for(int i = 0; i< 10; i++){
                lista.add(new Job());
            }
//            String str = executorService.invokeAny(lista);
//            System.out.println(str);
            List<Future<String>> list = executorService.invokeAll(lista);
            for(Future<String> future: list){
                System.out.println(future.get());
            }
//            Future<String> future1 = executorService.submit(new Job());
//            System.out.println(future1.get());
//            Future<String> future2 = executorService.submit(new Job());
//            Future<String> future3 = executorService.submit(new Job());
//            System.out.println(future2.get());
//            System.out.println(future3.get());
        }catch (Exception e){
            throw e;
        }finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }
    }

    public static class Job implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            return name + " : " + "My job" + " : " + nextInt;
        }
    }
}
