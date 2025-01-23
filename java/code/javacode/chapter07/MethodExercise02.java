
public class MethodExercise02 {
	

	public static void main(String[] args) {

		Person p = new Person();
		p.name = "jack";
		p.age = 19;

		MyTools myTools = new MyTools();
		Person p2 = myTools.copyPerson(p);

		System.out.println("p.mane=" + p.name + "\t" + "p.age" + p.age);
		System.out.println("p2.mane=" + p2.name + "\t" + "p2.age" + p2.age);
		System.out.println(p == p2);

	}
}

class Person {
	String name;
	int age;
}

class MyTools {

	public Person copyPerson (Person p1) {

		Person p2 = new Person();
			p2.name = p1.name;
			p2.age = p1.age;

		return p2;

	}
}