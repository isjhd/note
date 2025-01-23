
public class Homework03 {
	

	public static void main(String[] args) {

		Book book = new Book("春物", 125);
		book.info();
		book.updatePrice();
		book.info();
		
	}
}
class Book {

	String name;
	int money;

	public Book (String name, int money) {

		this.name = name;
		this.money = money;
	}

	public void updatePrice () {

		if (this.money > 150) {
			this.money = 150;
		} else if (this.money > 100) {
			this.money = 100;
		}
	}

	public void info () {
		System.out.println(this.name + "价格是" + this.money);
	}
}