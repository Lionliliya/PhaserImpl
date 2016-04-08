package ua.kiev.PhaserImplementation;


public class Main {
    public static void main(String[] args) throws InterruptedException {


        PhaserImplementation phaser = new PhaserImplementation();
        int[] values = new int[]{1, 2, 1, 1,};
        int numberOfThreads = 4;

        long result = phaser.getSquareSum(values, numberOfThreads);
        System.out.println("Total result is " + result);

    }
}
