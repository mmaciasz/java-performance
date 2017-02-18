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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    @BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void toMapCollect() {
        persons.stream().collect(Collectors.toMap(Person::getPersonId, person -> person));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void toMapParallelCollect() {
        persons.parallelStream().collect(Collectors.toMap(Person::getPersonId, person -> person));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void toMapUsingReduce() {
        final Map<Long, Person> result = persons.stream().reduce(new HashMap<Long, Person>(), (map, person) -> {
            map.put(person.getPersonId(), person);
            return map;
        }, (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        });
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void toMapUsingParallelReduce() {
        final Map<Long, Person> result = persons.parallelStream().reduce(new ConcurrentHashMap<Long, Person>(), (map, person) -> {
            map.put(person.getPersonId(), person);
            return map;
        }, (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        });
    }

    public static void main(String[] args) throws RunnerException {
        log.info("Beginning jmh tests...");
        Options opt = new OptionsBuilder()
                .include(MainBenchmark.class.getSimpleName())
                .shouldDoGC(true)
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(2)
                .syncIterations(true)
                .build();

        new Runner(opt).run();
    }
}
