public class a06_Volatile {
    private static int i = 0;
    private static boolean isReady = false;
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable);
        t0.start();
        i = 42;
        isReady = true;
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run(){
            while(!isReady){
                Thread.yield();
            }
            System.out.println(i);

        }
    }
}
