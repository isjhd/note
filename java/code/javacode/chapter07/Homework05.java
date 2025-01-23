
public class Homework05 {
	

	public static void main(String[] args) {

		Circle circle = new Circle(5);
		System.out.println("圆的周长是" + circle.len());
		System.out.println("圆的面积是" + circle.area());
	}
}
class Circle {

	double radius;

	public Circle (double radius) {
		this.radius = radius;
	}

	public double len () {//求周长

		return 2 * this.radius * 3.14;
	}

	public double area () {//求面积

		return 3.14 * radius * radius;
	}
}