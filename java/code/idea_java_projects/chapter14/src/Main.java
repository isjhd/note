
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.HashSet;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        HashSet set = new HashSet();
        set.add(new Person(1001,"CC"));
        set.add(new Person(1002,"AA"));
        set.add(new Person(1002,"Ab"));
    }
}
class Person {
    int i;
    String j;

    public Person(int i, String j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return i == person.i && Objects.equals(j, person.j);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "Person{" +
                "i=" + i +
                ", j='" + j + '\'' +
                '}';
    }
}