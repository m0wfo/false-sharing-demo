package com.logentries.blog;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Benchmark runner.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            printUsage();
        }

        switch (args[0]) {
            case "jmh":
                Options options = new OptionsBuilder()
                        .include(JMHBenchmark.class.getSimpleName())
                        .build();

                new Runner(options).run();
                break;
            case "plain":
                FalseSharingBenchmark.run();
                break;
            default:
                printUsage();
                break;
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar false-sharing-demo-1.0.0-SNAPSHOT.jar (jmh|plain)");
        System.exit(1);
    }
}
