# False sharing demo
Demonstration of false-sharing on the JVM

## Prerequisites

* Java 8+
* Maven 3+

## Usage

    mvn clean package
    java -XX:-RestrictContended -jar target/false-sharing-demo-1.0.0-SNAPSHOT.jar

The `-XX:-RestrictContended` flag is significant.

On a 2.3GHz Core i7 it takes just under a minute to run. You should get some output like:

    Updating unpadded version 1B times Took: 53.687116574sec
    Updating @Contended version 1B times Took: 6.985431589sec

