package data.test;

import data.test.dto.Person;

import java.util.List;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public class Main {

    public static void main(String[] args) {
        List<Person> persons = DataPrepare.prepareData(100000);
        System.out.println();
    }
}
