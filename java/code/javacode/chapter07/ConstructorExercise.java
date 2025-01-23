
public class ConstructorExercise {
	

	public static void main(String[] args) {

		Person p1 = new Person();
		System.out.println(p1.name + " " + p1.age);
		Person p2 = new Person("”≈Ω¥", 18);
		System.out.println(p2.name + " " + p2.age);
	}
}
class Person {

	String name;
	int age;

	public Person () {
		age = 18;
	}

	public Person (String pName, int pAge) {
		name = pName;
		age = pAge;
	}
}