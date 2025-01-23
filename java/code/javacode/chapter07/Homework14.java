import java.util.Random;
import java.util.Scanner;
public class Homework14 {
	
	public static void main(String[] args) {

		GuessingGame guessingGame = new GuessingGame();	
		for (int i = 1; i <= 3; i++) {
			int tomGamer = guessingGame.tomGamer();
			int comGuessNum = guessingGame.computerNum();
			System.out.println("第" + i + "局\t" + "玩家出" + tomGamer + "\t" + "电脑出" + comGuessNum);
			guessingGame.race(tomGamer, comGuessNum);
		}
	}
}
class GuessingGame {

	public int tomGamer () {//玩家
		Scanner myScanner = new Scanner(System.in);
		System.out.println("请输入 0-石头，1-剪刀，2-布");
		int tomGamer = myScanner.nextInt();
		return tomGamer;
	}

	public int computerNum () {//电脑
		Random r = new Random();
		int comGuessNum = r.nextInt(3);
		return comGuessNum;
	}

	public void race (int tomGamer, int comGuessNum) {
		if (tomGamer == 0 && comGuessNum == 1) {
			System.out.println("你赢了");
		} else if (tomGamer == 1 && comGuessNum == 2) {
			System.out.println("你赢了");
		} else if (tomGamer == 2 && comGuessNum == 0) {
			System.out.println("你赢了");
		} else if (tomGamer == comGuessNum) {
			System.out.println("平局");
		} else {
			System.out.println("你输了");
		}
	}
}