import java.util.Scanner;
public class SeqSearch {

	public static void main(String[] args) {

		String [] name = { "白眉鹰王", "金毛狮王", "紫衫龙王", "青翼蝠王"};

		int temp = -1;

		Scanner myScanner = new Scanner(System.in);
		System.out.println("请输入名字");
		String shu = myScanner.next();

		for (int i = 0; i < name.length; i++) {
			if (shu.equals(name[i])) {
				System.out.println("找到了" + name[i]);
				System.out.println("下标为" + i);

				temp = i;
				break;
			}
		}

		if ( temp == -1 ) {
			System.out.println("很抱歉，没找到" + shu);
		}
	}
}