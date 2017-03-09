package pl.com.pollub.test;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.pollub.test.map.MapOperations;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public class MainBenchmark {

    private static final Logger log;

    static {
        log = LoggerFactory.getLogger(MainBenchmark.class);
    }

    public static void main(String[] args) throws RunnerException {
        log.info("Beginning jmh tests...");
        Options opt = new OptionsBuilder()
                .include(MapOperations.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
