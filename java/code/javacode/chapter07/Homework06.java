
public class Homework06 {
	

	public static void main(String[] args) {

		Cale cale = new Cale(3, 9);
		System.out.println("和=" + cale.sum());
		System.out.println("差=" + cale.minus());
		System.out.println("乘=" + cale.mul());
		Double divRes = cale.div();
		if(divRes != null){
			System.out.println("除=" + cale.div());
		}

		
	}
}
class Cale {

	double a;
	double b;

	public Cale (double a, double b) {
		this.a = a;
		this.b = b;
	}

	public double sum () {
		return a + b;
	}

	public double minus () {
		return a - b;
	}

	public double mul () {
		return a * b;
	}

	public Double div () {

		if (b == 0) {
			System.out.println("不能为0");
			return null;
		} else {
			return a / b;
		}
	}
}