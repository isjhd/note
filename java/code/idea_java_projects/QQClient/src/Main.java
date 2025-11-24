/* @author  i-s-j-h-d
 * @version 1.0 */public class Main {
    public static void main(String[] args) {

        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        System.out.println(dog1.hashCode());
        System.out.println(dog2.hashCode());
    }
}
class Dog{}