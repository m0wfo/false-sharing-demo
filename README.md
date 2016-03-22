# False sharing demo
Demonstration of false-sharing on the JVM

## Prerequisites

* Java 8+
* Maven 3+

## Usage

    mvn clean package
    java -XX:-RestrictContended -jar target/false-sharing-demo-1.0.0-SNAPSHOT.jar plain

The `-XX:-RestrictContended` flag is significant.

On a 2.3GHz Core i7 it takes just under a minute to run. You should get some output like:

    Updating unpadded version 1B times Took: 53.687116574sec
    Updating @Contended version 1B times Took: 6.985431589sec

## JMH benchmark

    java -XX:-RestrictContended -jar target/false-sharing-demo-1.0.0-SNAPSHOT.jar plain

You should get some output like this:

    # Run complete. Total time: 00:01:11

    Benchmark                               Mode  Cnt          Score         Error  Units
    JMHBenchmark.padded                    thrpt   25  728583552.130 ± 4268366.821  ops/s
    JMHBenchmark.padded:updatePaddedA      thrpt   25  363537357.980 ± 2313491.132  ops/s
    JMHBenchmark.padded:updatePaddedB      thrpt   25  365046194.150 ± 2045685.311  ops/s
    JMHBenchmark.unpadded                  thrpt   25  148478851.964 ± 2746611.843  ops/s
    JMHBenchmark.unpadded:updateUnpaddedA  thrpt   25   74249061.512 ± 1926331.358  ops/s
    JMHBenchmark.unpadded:updateUnpaddedB  thrpt   25   74229790.452 ± 1126025.534  ops/s
