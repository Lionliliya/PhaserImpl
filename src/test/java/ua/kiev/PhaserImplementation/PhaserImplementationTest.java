package ua.kiev.PhaserImplementation;

import org.testng.annotations.Test;
import java.util.Random;
import static org.testng.Assert.assertEquals;

public class PhaserImplementationTest {

    @Test
    public void threadsEqualsElementsTest() throws InterruptedException {
        PhaserImplementation phaser = new PhaserImplementation();
        int[] values = new int[100];
        fillArray(values);
        int numberOfThreads = 100;

        long result = phaser.getSquareSum(values, numberOfThreads);
        long expected = calculateSquareSumInSungThread(values);
        assertEquals(result, expected);
        System.out.println("Result is " + result);
        System.out.println("Expected value is " + expected);

    }

    @Test
    public void threadsMoreThanValues() throws InterruptedException {
        PhaserImplementation phaser = new PhaserImplementation();
        int[] values = new int[50];
        fillArray(values);
        int numberOfThreads = 100;

        long result = phaser.getSquareSum(values, numberOfThreads);
        long expected = calculateSquareSumInSungThread(values);
        assertEquals(result, expected);
        System.out.println("Result is " + result);
        System.out.println("Expected value is " + expected);

    }

    @Test
    public void valuesMoreThanThreads() throws InterruptedException {
        PhaserImplementation phaser = new PhaserImplementation();
        int[] values = new int[50];
        fillArray(values);
        int numberOfThreads = 5;

        long result = phaser.getSquareSum(values, numberOfThreads);
        long expected = calculateSquareSumInSungThread(values);
        assertEquals(result, expected);
        System.out.println("Result is " + result);
        System.out.println("Expected value is " + expected);

    }

    public void fillArray(int [] values) {
        Random random = new Random();
        for (int i = 0; i < values.length ; i++) {
            values[i] = random.nextInt(2);
        }
    }

    public long calculateSquareSumInSungThread(int [] values) {
        long result = 0;
        for (int value : values) {
            result += Math.pow(value, 2);
        }
        return result;
    }


}