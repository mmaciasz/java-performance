package pl.com.pollub.test.sort;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import pl.com.pollub.test.Data;
import pl.com.pollub.test.dto.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mmaciasz on 2017-05-23.
 */
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@CompilerControl(CompilerControl.Mode.DONT_INLINE)
@State(Scope.Thread)
public class SortTest {

    private static final Comparator<Person> personsComparator =
            new Comparator<Person>() {
                @Override
                public int compare(final Person first, final Person second) {
                    return first.getFirstName().compareTo(second.getFirstName());
                }
            };

    @Benchmark
    public List<Person> newSortList() {
        final List<Person> persons = Data.getPersons();
        persons.sort(Comparator.comparing(Person::getFirstName));
        return persons;
    }

    @Benchmark
    public List<Person> oldSortList() {
        final List<Person> persons = Data.getPersons();
        Collections.sort(persons, personsComparator);
        return persons;
    }

}
