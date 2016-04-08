package ua.kiev.PhaserImplementation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class PhaserImplementation implements SquareSum {
    static ExecutorService executor = null;
    static Phaser phaser = null;
    static List<Callable<Long>> callableList = null;

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) throws InterruptedException {
        long result = 0;

        executor = Executors.newFixedThreadPool(numberOfThreads);
        phaser = new Phaser(numberOfThreads);
        callableList = new ArrayList<>();

        /**breaking up given array value[] into parts and filling callableList
        with new Callable with small parts of given array **/

        int [] withTail;
        int startIndex = 0;
        int tail = values.length % numberOfThreads;
        int step = (values.length - tail)/numberOfThreads;

        for (int j = 0; j < numberOfThreads ; j++) {
            int[] noTail = Arrays.copyOfRange(values, startIndex, startIndex + step);

            if (tail > 0) {
                withTail = Arrays.copyOfRange(values, startIndex, startIndex + step + 1);
                callableList.add(new CalculateRunnable(withTail, phaser));
                tail--;
                startIndex += step + 1;
            } else {
                startIndex += step;
                callableList.add(new CalculateRunnable(noTail, phaser));
            }
        }

        /**Executing of all threads that calculate method**/

        List<Future<Long>> futureList = executor.invokeAll(callableList);

        for (Future<Long> longFuture : futureList) {
            try {
                phaser.arriveAndDeregister();
                System.out.println("Phaser deregistered");
                result += longFuture.get();

            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }

        return result;
    }

}
