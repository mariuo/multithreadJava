import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class a13_ReentrantReadWriteLock {
    private static int i = -1;
    private static ReadWriteLock readWriteLock= new ReentrantReadWriteLock();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable r1 = () ->{
            Lock writeLock = readWriteLock.writeLock();
            writeLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println("Writing: "+ name + " # "+i);
            i++;
            System.out.println("Writed: "+ name + " # "+i);
            writeLock.unlock();

        };
        Runnable r2 = () ->{
            Lock readLock = readWriteLock.readLock();
            readLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println("Reading: "+name + " # "+i);
            System.out.println("Readed: "+name + " # "+i);
            readLock.unlock();

        };

        for(int i = 0; i < 6; i++){
            executorService.execute(r1);
            executorService.execute(r2);
        }
        executorService.shutdown();

    }
}
