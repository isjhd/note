
public class Homework01 {

	public static void main(String[] args) {

		int count = 0;//累积过的路口
		double money = 100000;//还有多少钱

		while (true){ //无限循环

			if (money > 50000) {
				money *= 0.95;
				count++;

			}else if(money >= 1000){
				money -= 1000;
				count++;

			}else{
				break;
			}
		}
		
		System.out.println("100000可以过" + count + "路口..");


	}
}