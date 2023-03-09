import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class a15_ProductorConsumer_1 {
    private static final List<Integer> LIST = new ArrayList<>(5);
    private static boolean isProducting = true;
    private static boolean isConsuming = true;

    public static void main(String[] args) {
        Thread productor = new Thread(() -> {
            while (true) {
                try{
                simulationProcess();
                    if (isProducting) {
                        System.out.println("Producting...");
                        int num = new Random().nextInt(10000);
                        LIST.add(num);
//                        System.out.println("Added...: " + num);

                        if (LIST.size() == 5) {
                            System.out.println("Pause - Productor...");
                            isProducting = false;
                        }
                        if (LIST.size() == 1) {
                            System.out.println("Starting - Consumer...");
                            isConsuming = true;
                        }
                    } else {
                        System.out.println("!!! Productor sleeping...!");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        });
        Thread consumer = new Thread(() -> {
            while (true) {
                try{
                    simulationProcess();
                    if (isConsuming) {
                        System.out.println("Consuming...");
                        Optional<Integer> num = LIST.stream().findFirst();
                        num.ifPresent(n -> {
                            LIST.remove(n);
                        });
//                        System.out.println("Removed...: " + num.get());
                        if (LIST.size() == 0) {
                            System.out.println("Pause - Consumer...");
                            isConsuming = false;
                        }
                        if (LIST.size() == 4) {
                            System.out.println("Starting - Productor...");
                            isProducting = true;
                        }
                    } else {
                        System.out.println("??? Consumer sleeping...!");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        a12_Window.monitore(()-> String.valueOf(LIST.size()));

        productor.start();
        consumer.start();
    }

    private static void simulationProcess() {
        int temp = new Random().nextInt(40);
        try {
            Thread.sleep(temp);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
