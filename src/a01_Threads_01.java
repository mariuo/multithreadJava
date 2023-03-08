public class a01_Threads_01 {
    public static void main(String[] args) {
        //current thread
        Thread t = Thread.currentThread();
        System.out.println(t.getName());

        a01_MyRunnable a01MyRunnable = new a01_MyRunnable();
        //new thread
        Thread t0 = new Thread(a01MyRunnable);
//        t0.run(); // run in the same thread
//        t0.start(); // executing a new thread

        //Runnable with lambda
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
//        t1.start();
//        t2.start(); Cant init 2x the same thread.

        //Others threads
        Thread t2 = new Thread(a01MyRunnable);

        t0.start(); // executing a new thread
        t1.start();
        t2.start();



    }
}