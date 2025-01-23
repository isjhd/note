
public class ArrayReverse {

	public static void main(String[] args) {

		int [] arr = {11,22,33,44,55,66};

		int [] temp = new int [arr.length];
		
		for ( int i = 0; i < arr.length; i++ ) {
			temp [i] = arr[arr.length - 1 - i];
		}

		arr =temp;

		for ( int i = 0; i < arr.length; i++ ) {
			System.out.println(arr[i]);
		}
	}
}
	
