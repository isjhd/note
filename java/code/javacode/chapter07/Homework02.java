
public class Homework02 {
	

	public static void main(String[] args) {

		A02 a02 = new A02();
		String [] strs = {"波奇", "娜美", "雪之下"};
		System.out.println(a02.find(strs, "娜美"));
	}
}

class A02 {

	public int find (String [] strs, String findStr) {

		for(int i = 0; i < strs.length; i++){

			if (findStr.equals(strs[i])) {

				return i;
			}
		}

			return -1;
	}
}