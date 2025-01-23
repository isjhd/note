
public class Homework04 {

	public static void main(String[] args) {

		int number = 153;
		int ge = number%10;
		int shi = number/10%10;
		int ba = number/100;

		if ( number == ba*ba*ba + shi*shi*shi + ge*ge*ge) {
			System.out.println("ÊÇ");
		}else{
			System.out.println("·ñ");
		}

	}
}