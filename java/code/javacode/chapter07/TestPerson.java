
public class TestPerson {
	

	public static void main(String[] args) {

		Person p1 = new Person("∫Õ’Ê", 18);
		Person p2 = new Person("πÌ–Û", 19);
		System.out.println(p1.compareTo(p2));
	}
}

class Person{

	String name;
	int age;

	public Person (String name , int age) {
		this.name = name;
		this.age = age;
	}

	public boolean compareTo(Person p) {

		return this.name == p.name && this.age == p.age;
	} 
}