import java.util.Scanner;
public class SeqSearch {

	public static void main(String[] args) {

		String [] name = { "��üӥ��", "��ëʨ��", "��������", "��������"};

		int temp = -1;

		Scanner myScanner = new Scanner(System.in);
		System.out.println("����������");
		String shu = myScanner.next();

		for (int i = 0; i < name.length; i++) {
			if (shu.equals(name[i])) {
				System.out.println("�ҵ���" + name[i]);
				System.out.println("�±�Ϊ" + i);

				temp = i;
				break;
			}
		}

		if ( temp == -1 ) {
			System.out.println("�ܱ�Ǹ��û�ҵ�" + shu);
		}
	}
}