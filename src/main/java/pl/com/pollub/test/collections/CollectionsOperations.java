package pl.com.pollub.test.collections;

import javafx.util.Pair;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import pl.com.pollub.test.Data;
import pl.com.pollub.test.dto.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmaciasz on 2017-05-23.
 */
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class CollectionsOperations {

    @Benchmark
    public List<Pair<Person, Person>> pairPersons() {
        return Data.getPersons().stream()
                .reduce(new ArrayList<>(), (sum, element) -> {
                    sum.add(new Pair<>(element, element.getPartner()));
                    return sum;
                }, (sum1, sum2) -> {
                    sum1.addAll(sum2);
                    return sum1;
                });
    }
}
