package pl.com.pollub.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.pollub.test.dto.Person;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public class MainBenchmark {

    private static final Logger log;

    private static List<Person> persons;

    static {
        log = LoggerFactory.getLogger(MainBenchmark.class);
        persons = DataPrepare.prepareData(100000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void getData() {
        DataPrepare.prepareData(10000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void mapTest() {
        persons.stream().collect(Collectors.toMap(Person::getPersonId, person -> person));
    }

    public static void main(String[] args) throws RunnerException {
        log.error("Beginning jmh tests...");
        Options opt = new OptionsBuilder()
                .include(MainBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
