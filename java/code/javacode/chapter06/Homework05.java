
public class Homework05 {
	

	public static void main(String[] args) {

		int [] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
		 	arr[i] = (int)(Math.random()*100)+1;
		 }

		 int max = arr[0];
		int maxIndex = 0;
		System.out.println("====arr���ֵ���±�=======");
		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
				maxIndex = i;
			}
		}

		System.out.println(max + " " + maxIndex);


		System.out.println("====arr��Ԫ�����======");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println("\n====arr��Ԫ�����������======");
		for (int i = arr.length-1; i >= 0; i--) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
		double sun = 0;
		System.out.println("====arr��ƽ��ֵ=======");
		for (int i = 0; i < arr.length; i++) {
			sun += arr[i];
		}
		System.out.println(sun/arr.length);

		int findNum = 8;
		int index = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == findNum) {
				System.out.println("�ҵ���" + arr[i] + "�±�Ϊ" + i);
				index = i;
			}
		}
			if (index == -1) {
				System.out.println("û���ҵ�8");
			}


	}
}