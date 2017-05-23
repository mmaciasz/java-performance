package pl.com.pollub.test.collections;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import pl.com.pollub.test.Data;
import pl.com.pollub.test.constants.Names;
import pl.com.pollub.test.dto.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by mmaciasz on 2017-03-09.
 */
@State(Scope.Thread)
public class MapOperations {

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public List<Person> toMapCollect() {
        final Map<Long, Person> personsMap = Data.getPersons().stream()
                .collect(Collectors.toMap(Person::getPersonId, person -> person));
        final List<Person> ret = personsMap.entrySet().stream()
                .filter(p -> p.getValue().getFirstName().equals(Names.Aaliyah.name()))
                .collect(ArrayList<Person>::new,
                        (list, p) -> list.add(p.getValue()),
                        ArrayList::addAll);
        return ret;
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void toMapWithoutCollect(Blackhole bh) {
        final Map<Long, Person> personsMap = Data.getPersons().stream()
                .collect(Collectors.toMap(Person::getPersonId, person -> person));
        personsMap.entrySet().stream()
                .filter(p -> p.getValue().getFirstName().equals(Names.Aaliyah.name()))
                .forEach(bh::consume);
    }


    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public Map<Object, Object> toMapUsingReduce() {
        final Map<Object, Object> ret = Data.getPersons().stream()
                .reduce(new HashMap<>(), (map, person) -> {
                    map.put(person.getPersonId(), person);
                    return map;
                }, (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        return ret;
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public Map<Long, Person> toMapUsingParallelReduce() {
        final Map<Long, Person> ret = Data.getPersons().parallelStream()
                .reduce(new ConcurrentHashMap<Long, Person>(), (map, person) -> {
                    map.put(person.getPersonId(), person);
                    return map;
                }, (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        return ret;
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public Map<Long, Person> toMapParallelCollect() {
        return Data.getPersons().parallelStream()
                .collect(Collectors.toMap(Person::getPersonId, person -> person));
    }
}
