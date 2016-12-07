package data.test.constants;

import java.util.Objects;

/**
 * Created by mmaciasz on 2016-12-07.
 */
public enum Names {

    Sophia(0),
    Jackson(1),
    Emma(2),
    Aiden(3),
    Olivia(4),
    Lucas(5),
    Ava(6),
    Liam(7),
    Mia(8),
    Noah(9),
    Isabella(10),
    Ethan(11),
    Riley(12),
    Mason(13),
    Aria(14),
    Caden(15),
    Zoe(16),
    Oliver(17),
    Charlotte(18),
    Elijah(19),
    Lily(20),
    Grayson(21),
    Layla(22),
    Jacob(23),
    Amelia(24),
    Michael(25),
    Emily(26),
    Benjamin(27),
    Madelyn(28),
    Carter(29),
    Aubrey(30),
    James(31),
    Adalyn(32),
    Jayden(33),
    Madison(34),
    Logan(35),
    Chloe(36),
    Alexander(37),
    Harper(38),
    Caleb(39),
    Abigail(40),
    Ryan(41),
    Aaliyah(42),
    Luke(43),
    Avery(44),
    Daniel(45),
    Evelyn(46),
    Jack(47),
    Kaylee(48),
    William(49),
    Ella(50),
    Owen(51),
    Ellie(52),
    Gabriel(53),
    Scarlett(54),
    Matthew(55),
    Arianna(56),
    Connor(57),
    Hailey(58),
    Jayce(59),
    Nora(60),
    Isaac(61),
    Addison(62),
    Sebastian(63),
    Brooklyn(64),
    Henry(65),
    Hannah(66),
    Muhammad(67),
    Mila(68),
    Cameron(69),
    Leah(70),
    Wyatt(71),
    Elizabeth(72),
    Dylan(73),
    Sarah(74),
    Nathan(75),
    Eliana(76),
    Nicholas(77),
    Mackenzie(78),
    Julian(79),
    Peyton(80),
    Eli(81),
    Maria(82),
    Levi(83),
    Grace(84),
    Isaiah(85),
    Adeline(86),
    Landon(87),
    Elena(88),
    David(89),
    Anna(90),
    Christian(91),
    Victoria(92),
    Andrew(93),
    Camilla(94),
    Brayden(95),
    Lillian(96),
    John(97),
    Natalie(98),
    Lincoln(99);

    private Integer number;

    Names(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public static String getByNumber(final Integer nr) {
        for (Names name : Names.values()) {
            if (Objects.equals(name.getNumber(), nr)) {
                return name.name();
            }
        }
        throw new IllegalArgumentException();
    }
}
