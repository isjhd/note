
public class MethodExercise01 {
	

	public static void main(String[] args) {

		AA b = new AA ();
		if (b.odd (5)) {
			System.out.println("是偶数");
		}else {
			System.out.println("是奇数");
		}


		AA d = new AA();
		d.print(6,8,'#');
	}
}

class AA {
	public boolean odd (int n) {
		// if ( n % 2 == 0 ) {
		// 	return true;
		// }else {
		// 	return false;
		// }
		
		//return n % 2 == 0 ? true : false;
		
		return n % 2 == 0;
	}

	public void print (int row, int col, char c) {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}