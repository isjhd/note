
public class OverLoadExercise {
	

	public static void main(String[] args) {

		Methods method = new Methods();
		method.m(5);
		method.m(5, 6);
		method.m("ÄãºÃ");
		System.out.println("=======================================");
		System.out.println(method.max(10, 20));
		System.out.println(method.max(10.0, 20.0));
		System.out.println(method.max(10.0, 20.0, 30.0));
	}
}

class Methods {

	public void m(int num){
		System.out.println(num * num);
	}

	public void m(int a, int b){
		System.out.println(a * b);
	}

	public void m(String str){
		System.out.println(str);
	}

	public int max (int a, int b){

		return a > b ? a : b;
	}

	public double max (double a, double b){

		return a > b ? a : b;
	}

	public double max (double a, double b, double c) {

		double max1 = a > b ? a : b;
		return max1 > c ? max1 : c;
	}
}