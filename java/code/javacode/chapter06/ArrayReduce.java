
import java.util.Scanner;
public class ArrayReduce {

	public static void main(String[] args) {

		Scanner myScanner = new Scanner(System.in);

		int [] arr = { 1, 2, 3, 4 };

		do{
			int [] arrNew = new int[arr.length - 1];

			for (int i = 0; i < arr.length - 1; i++) {
				arrNew[i] = arr[i];
			}

			
			arr = arrNew;

			System.out.println("=====arr删减后的元素=======");
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + "\t");
			}

			System.out.println("是否继续删减 y/n ");
			char key = myScanner.next().charAt(0);
			if ( key == 'n' ) {
				break;
			}else if ( arr.length == 1 ){
				break;
			}

		}while(true);

		System.out.println("只剩一个元素,不能再删减");
	}
}