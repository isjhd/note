
public class Constructor01 {
	

	public static void main(String[] args) {

		Person p1 = new Person("би╫╢", 18);
		Person p2 = new Person("се╫╢"); 
		System.out.println(p1.name);
		System.out.println(p1.age);
		System.out.println(p2.name);

		Dog d = new Dog();


	}
}

class Dog {

	public Dog (int age){

	}

	Dog (){
		
	}
}

class Person {

	String name;
	int age;

	public Person(String pname, int page){
	
		name = pname;
		age = page;
	}

	public Person(String pname){

		name = pname;
	}
}