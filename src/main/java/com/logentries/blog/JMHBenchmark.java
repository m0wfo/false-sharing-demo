package com.logentries.blog;

import org.openjdk.jmh.annotations.*;
import sun.misc.Contended;

/**
 * Created by chris on 22/03/2016.
 */
@Fork(value = 1, jvmArgsPrepend = "-XX:-RestrictContended")
@Warmup(iterations = 10)
@Measurement(iterations = 25)
@Threads(2)
public class JMHBenchmark {

    @State(Scope.Group)
    public static class Unpadded {
        public long a;
        public long b;
    }

    @State(Scope.Group)
    public static class Padded {
        @Contended
        public long a;
        public long b;
    }

    @Group("unpadded")
    @GroupThreads(1)
    @Benchmark
    public long updateUnpaddedA(Unpadded u) {
        return u.a++;
    }

    @Group("unpadded")
    @GroupThreads(1)
    @Benchmark
    public long updateUnpaddedB(Unpadded u) {
        return u.b++;
    }

    @Group("padded")
    @GroupThreads(1)
    @Benchmark
    public long updatePaddedA(Padded u) {
        return u.a++;
    }

    @Group("padded")
    @GroupThreads(1)
    @Benchmark
    public long updatePaddedB(Padded u) {
        return u.b++;
    }
}
