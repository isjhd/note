
public class Homework01 {

	public static void main(String[] args) {

		int count = 0;//�ۻ�����·��
		double money = 100000;//���ж���Ǯ

		while (true){ //����ѭ��

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
		
		System.out.println("100000���Թ�" + count + "·��..");


	}
}