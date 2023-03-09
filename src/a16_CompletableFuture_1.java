import java.util.concurrent.CompletableFuture;

public class a16_CompletableFuture_1 {
    public static void main(String[] args) {
        CompletableFuture<String> process = processMethod();

        CompletableFuture<String> thenApply =
                process.thenApply(s -> s + "Enjoy");

        CompletableFuture<Void> thenAccept =
                thenApply.thenAccept(s -> System.out.println(s));

        System.out.println("okok");

        sleep();
        sleep();
        sleep();
    }

    private static CompletableFuture<String> processMethod() {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return "Subscribe ";
        });
    }

    private static final void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
