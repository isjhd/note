
public class Homework05 {
	

	public static void main(String[] args) {

		Circle circle = new Circle(5);
		System.out.println("Բ���ܳ���" + circle.len());
		System.out.println("Բ�������" + circle.area());
	}
}
class Circle {

	double radius;

	public Circle (double radius) {
		this.radius = radius;
	}

	public double len () {//���ܳ�

		return 2 * this.radius * 3.14;
	}

	public double area () {//�����

		return 3.14 * radius * radius;
	}
}