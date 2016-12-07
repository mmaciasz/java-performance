package data.test.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public class Person {

    private Long personId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Sex sex;
    private LocalDate birthDate;
    private Integer growth;
    private List<Person> childrens;
    private Person partner;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public List<Person> getChildrens() {
        if (childrens == null) {
            childrens = new ArrayList<>();
        }
        return childrens;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }
}
