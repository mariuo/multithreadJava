import java.util.ArrayList;
import java.util.List;

public class a03_SyncCollections {

    private static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
//        list = Collections.synchronizedList(list);
        MyRunnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);

        System.out.println(list);


    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run(){
            list.add("Subscribe");
            String name = Thread.currentThread().getName();
            System.out.println(name + " inserted list");

        }

    }

}
