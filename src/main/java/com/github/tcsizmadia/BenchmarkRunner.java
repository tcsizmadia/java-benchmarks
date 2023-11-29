package com.github.tcsizmadia;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(ConcurrentLinkedQueueBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("benchmarkResults.csv")
                .build();

        new org.openjdk.jmh.runner.Runner(opt).run();
    }
}
