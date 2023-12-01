package com.github.tcsizmadia;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5)
@Measurement(iterations = 100)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(org.openjdk.jmh.annotations.Scope.Thread)
@Fork(1)
public class ConcurrentLinkedQueueBenchmark
{
    static class Payload {
        private final int id;
        private final String value;

        public Payload(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }

    private ConcurrentLinkedQueue<Integer> queue;
    private List<Payload> list;

    @Setup
    public void setup() {
        queue = new ConcurrentLinkedQueue<>();
        list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(new Payload(i, "value" + i));
        }
    }
    @Param({"1000", "5000", "10000", "50000", "100000", "500000"})
    public int listSize;

    @Benchmark
    public void testAddAllWithStream(Blackhole blackhole) {
        queue.clear();
        queue.addAll(list.stream().map(Payload::getId).toList());

        blackhole.consume(queue);
    }

    @Benchmark
    public void testAddAllWithFor(Blackhole blackhole) {
        queue.clear();
        for (Payload payload : list) {
            queue.add(payload.getId());
        }

        blackhole.consume(queue);
    }

    @Benchmark
    public void testAddAllWithParallelStream(Blackhole blackhole) {
        queue.clear();
        queue.addAll(list.parallelStream().map(Payload::getId).toList());

        blackhole.consume(queue);
    }

    @Benchmark
    public void testAddAllWithStreamForEach(Blackhole blackhole) {
        queue.clear();
        list.stream().map(Payload::getId).forEach(queue::add);

        blackhole.consume(queue);
    }

    @Benchmark
    public void testAddAllWithParallelStreamForEach(Blackhole blackhole) {
        queue.clear();
        list.parallelStream().map(Payload::getId).forEach(queue::add);

        blackhole.consume(queue);
    }

    @TearDown
    public void tearDown() {
        queue = null;
        list = null;
    }
}
