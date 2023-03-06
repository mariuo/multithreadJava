public class Synchronized_01 {
    static int i = -1;

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        Thread t3 = new Thread(myRunnable);
        Thread t4 = new Thread(myRunnable);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
    public static void print(){
        synchronized (Synchronized_01.class){
        i++;
        String name = Thread.currentThread().getName();
        System.out.println(name + ":"+ i);

        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run(){
            print();

        }
//        @Override
//        public synchronized void run(){
//            i++;
//            String name = Thread.currentThread().getName();
//            System.out.println(name + ":"+ i);
//
//        }
//        @Override
//        public void run(){
//            i++;
//            String name = Thread.currentThread().getName();
//            System.out.println(name + ":"+ i);
//
//        }

//        static Object lock1 = new Object();
//        static Object lock2 = new Object();
//
//        @Override
//        public void run() {
////            System.out.println("ab");
//            synchronized (lock1) {
//                i++;
//                String name = Thread.currentThread().getName();
//                System.out.println(name + ":" + i);
//
//            }
//            synchronized (lock2) {
//                i++;
//                String name = Thread.currentThread().getName();
//                System.out.println(name + ":" + i);
//
//            }
//        }
    }
}
