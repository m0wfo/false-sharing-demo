package com.logentries.blog;

public class FalseSharingBenchmark {

    private static final long ITERATIONS = 1_000_000_000;

    private static final SomeClass unpadded = new SomeClass();
    private static final SomePaddedClass padded = new SomePaddedClass();

    public static void run() throws Exception {
        double unpaddedRun = updateValues(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                unpadded.valueA++;
            }
        }, () -> {
            for (int i = 0; i < ITERATIONS; i++) {
                unpadded.valueB++;
            }
        });

        double paddedRun = updateValues(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                padded.valueA++;
            }
        }, () -> {
            for (int i = 0; i < ITERATIONS; i++) {
                padded.valueB++;
            }
        });

        System.out.println("Updating unpadded version 1B times Took: " + unpaddedRun + "sec");
        System.out.println("Updating @Contended version 1B times Took: " + paddedRun + "sec");
    }

    private static double updateValues(Runnable readerTask, Runnable writerTask) throws Exception {
        long start = System.nanoTime();
        Thread reader = new Thread(readerTask);
        Thread writer = new Thread(writerTask);

        reader.start();
        writer.start();

        reader.join();
        writer.join();

        return (System.nanoTime() - start) / 1E9;
    }
}
