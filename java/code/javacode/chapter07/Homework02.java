
public class Homework02 {
	

	public static void main(String[] args) {

		A02 a02 = new A02();
		String [] strs = {"����", "����", "ѩ֮��"};
		System.out.println(a02.find(strs, "����"));
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