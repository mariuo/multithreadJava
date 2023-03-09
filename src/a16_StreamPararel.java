import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class a16_StreamPararel {
    public static void main(String[] args) {
        Instant init = Instant.now();

        Map<Double, Double> mapmap = new ConcurrentHashMap<>();

        IntStream.range(1, 10_000_000)
                .parallel()
                .mapToDouble(num -> Math.pow(num, 5))
                .filter(num -> num % 2 == 0)
                .forEach(num -> mapmap.put(num, Math.pow(num, 5)));

        Instant end = Instant.now();

        System.out.println(Duration.between(init, end));
    }
}
