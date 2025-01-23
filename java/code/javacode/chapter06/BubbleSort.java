
public class BubbleSort {

	public static void main(String[] args) {

		int [] arr = { 24, 69, 80, 57, 13 };

		int temp = 0;

		for (int j = 1; j < arr.length; j++) {
			for (int i = 0; i < arr.length - j; i++) {
				
				if (arr[i] > arr[i+1]) {
					temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print( arr[i] + "\t" );
		}
	}
}