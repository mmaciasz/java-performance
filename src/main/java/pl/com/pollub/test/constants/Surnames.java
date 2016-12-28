package pl.com.pollub.test.constants;

import java.util.Objects;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public enum Surnames {

    Brown(0),
    Smiths(1),
    Patel(2),
    Jone(3),
    William(4),
    Johnson(5),
    Taylor(6),
    Thoma(7),
    Robert(8),
    Khan(9),
    Lewi(10),
    Jackson(11),
    Clarke(12),
    Jame(13),
    Phillip(14),
    Wilson(15),
    Ali(16),
    Mason(17),
    Mitchell(18),
    Rose(19),
    Davis(20),
    Davie(21),
    Rodríguez(22),
    Cox(23),
    Alexander(24),
    Jones(25),
    Williams(26),
    Davies(27),
    Evans(28),
    Thomas(29),
    Roberts(30),
    Lewis(31),
    Hughes(32),
    Morgan(33),
    Griffiths(34),
    Edwards(35),
    Smith(36),
    James(37),
    Rees(38),
    Jenkins(39),
    Owen(40),
    Price(41),
    Phillips(42),
    Moss(43),
    Driscoll(44);

    private Integer number;

    Surnames(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public static String getByNumber(final Integer nr) {
        for (Surnames surname : Surnames.values()) {
            if (Objects.equals(surname.getNumber(), nr)) {
                return surname.name();
            }
        }
        throw new IllegalArgumentException();
    }
}
