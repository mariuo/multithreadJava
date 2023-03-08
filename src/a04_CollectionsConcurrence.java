import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class a04_CollectionsConcurrence {

    //When modify the list, it will copy all complete array everytime.
//    private static List<String> list = new CopyOnWriteArrayList<>();
//    private static Map<Integer, String> stringMap = new ConcurrentHashMap<>();
    private static BlockingQueue<String> queueString = new LinkedBlockingQueue<>();
    private static List<Integer> scores = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        //Collection thread-safe
        MyRunnable myRunnable = new MyRunnable();
        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);
//        System.out.println(stringMap);
        System.out.println(queueString);
        var array = scores.stream().toArray();


    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run(){
//            stringMap.put(new Random().nextInt(3),"Like this video.");
            queueString.add("If have condition!");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inserted queue");

        }

    }

}
