package pl.com.pollub.test;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import pl.com.pollub.test.dto.Person;

import java.util.List;

/**
 * Created by mmaciasz on 2017-03-09.
 */
@State(Scope.Thread)
public class Data {

    private static List<Person> persons;

    static {
        persons = DataPrepare.prepareData(100000);
    }

    public static List<Person> getPersons() {
        return persons;
    }
}
