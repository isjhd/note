import java.util.Random;
import java.util.Scanner;
public class Homework14 {
	
	public static void main(String[] args) {

		GuessingGame guessingGame = new GuessingGame();	
		for (int i = 1; i <= 3; i++) {
			int tomGamer = guessingGame.tomGamer();
			int comGuessNum = guessingGame.computerNum();
			System.out.println("��" + i + "��\t" + "��ҳ�" + tomGamer + "\t" + "���Գ�" + comGuessNum);
			guessingGame.race(tomGamer, comGuessNum);
		}
	}
}
class GuessingGame {

	public int tomGamer () {//���
		Scanner myScanner = new Scanner(System.in);
		System.out.println("������ 0-ʯͷ��1-������2-��");
		int tomGamer = myScanner.nextInt();
		return tomGamer;
	}

	public int computerNum () {//����
		Random r = new Random();
		int comGuessNum = r.nextInt(3);
		return comGuessNum;
	}

	public void race (int tomGamer, int comGuessNum) {
		if (tomGamer == 0 && comGuessNum == 1) {
			System.out.println("��Ӯ��");
		} else if (tomGamer == 1 && comGuessNum == 2) {
			System.out.println("��Ӯ��");
		} else if (tomGamer == 2 && comGuessNum == 0) {
			System.out.println("��Ӯ��");
		} else if (tomGamer == comGuessNum) {
			System.out.println("ƽ��");
		} else {
			System.out.println("������");
		}
	}
}