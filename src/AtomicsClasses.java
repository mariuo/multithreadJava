import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicsClasses {
    static AtomicInteger i = new AtomicInteger(-1);
    static AtomicLong l = new AtomicLong(-1);
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t0.start();
        t1.start();
        t2.start();

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run(){

           String name = Thread.currentThread().getName();

           System.out.println(name + " : " + i.incrementAndGet() + " ; " + l.incrementAndGet());

        }
    }
}
