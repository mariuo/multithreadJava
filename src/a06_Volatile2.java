public class a06_Volatile2 {
//    private static int i = 0;
//    private static boolean isReady = false;

    //Uses the memory RAM. in the place of Cache from Process.
    private static volatile int i = 0;
    private static volatile boolean isReady = false;
    public static void main(String[] args) {
        while(true){
        MyRunnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable);
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        t0.start();
        t1.start();
        t2.start();

        i = 42;
        isReady = true;

        while(
                t0.getState() != Thread.State.TERMINATED
                || t1.getState() != Thread.State.TERMINATED
                || t2.getState() != Thread.State.TERMINATED
        ){

        }
            i = 0;
            isReady = false;

        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run(){
            while(!isReady){
                Thread.yield();
            }
            if(i != 42){
                throw new IllegalArgumentException("Heyyy is not 42.");
            }

        }
    }
}
