package lessons.test444;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by SBT-Klyshov-DA on 14.06.2018.
 */
public class B {
    public static void main(String[] args) {
        //Runnable r = () -> System.out.println("Это Runnable с помощью лямбд");
        //r.run();
        int sum = Arrays.asList(1,2,3,4).stream().reduce(3, (x, y) -> x+y);
        System.out.println(sum);
        //List<Integer> list= Arrays.asList(1,2,3,4);
        //list.add(5);
        //System.out.println(list.get(4));

        class Person {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return name;
            }
        }

        List<Person> persons =
                Arrays.asList(
                        new Person("Andrew", 20),
                        new Person("Igor", 23),
                        new Person("Ira", 23),
                        new Person("Vitia", 12));
        List<Person> filtered =
                persons
                        .stream()
                        .filter(p -> p.name.startsWith("I"))
                        .collect(Collectors.toList());

        //System.out.println(filtered);


        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (n1, n2) -> n1 + ";" + n2
                )
         );

        System.out.println(map);

        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // ANDREW | IGOR | IRA | VITIA
    }
}