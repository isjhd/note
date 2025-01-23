
public class ForExercise {

	public static void main(String[] args) {

		int zho = 7;
		for ( int i = 1; i <= (zho+1)/2; i++ ) {
			
			for ( int k = 1; k <= (zho+1)/2 - i; k++ ) {
				System.out.print(" ");
			}

			for (int j = 1; j <= 2 * i - 1; j++ ) {

				if ( j == 1 || j == 2 * i - 1 ) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
				
			}
			System.out.println(" ");
		}

		for ( int i = 1; i <= (zho-1)/2; i++ ) {
			
			for ( int k = 1; k <= i - 1; k++ ) {
				System.out.print(" ");
			}

			for (int j = 0; j <= 2 * ((zho+1)/2 - i) - 1; j++ ) {

				if ( j == 1 || j == 2 * ((zho+1)/2 - i) - 1 ) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
				
			}
			System.out.println(" ");
		}






	}
}