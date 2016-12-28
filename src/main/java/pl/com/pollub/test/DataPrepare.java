package pl.com.pollub.test;


import pl.com.pollub.test.constants.Names;
import pl.com.pollub.test.constants.Surnames;
import pl.com.pollub.test.dto.Person;
import pl.com.pollub.test.dto.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public class DataPrepare {

    public static List<Person> prepareData(int listSize) {
        List<Person> persons = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < listSize; i++) {
            Person person = generatePerson(rand);
            person.setPersonId((long) i);
            persons.add(person);
        }
        List<Person> childrens = new LinkedList<>(persons);
        persons.forEach(p -> {
            int j = rand.nextInt(6);
            while (j > 0) {
                p.getChildrens().add(childrens.get(rand.nextInt(listSize)));
                j--;
            }
            Person partner = persons.get(rand.nextInt(listSize));
            while (p.getSex().equals(partner.getSex())) {
                partner = persons.get(rand.nextInt(listSize));
            }
            p.setPartner(partner);
        });
        return persons;
    }

    private static Person generatePerson(Random rand) {
        Person person = new Person();
        person.setFirstName(Names.getByNumber(rand.nextInt(100)));
        person.setLastName(Surnames.getByNumber(rand.nextInt(45)));
        person.setAge(rand.nextInt(120));
        person.setSex(rand.nextInt(2) == 0 ? Sex.FEMALE : Sex.MALE);
        LocalDate birthDate = LocalDate.of(1900 + rand.nextInt(100), 1 + rand.nextInt(12), 1 + rand.nextInt(28));
        person.setBirthDate(birthDate);
        person.setGrowth(150 + rand.nextInt(50));
        return person;
    }
}
