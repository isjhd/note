
public class VarParameterExercise {
	

	public static void main(String[] args) {

		HspMethod hspMethod = new HspMethod();
		System.out.println(hspMethod.showScore("С��", 70, 80));
		System.out.println(hspMethod.showScore("С��", 70, 80, 90));
		System.out.println(hspMethod.showScore("С��", 70, 80, 90, 100));
	}
}

class HspMethod {

	public String showScore (String name, int...sun) {
		int rea = 0;
		for ( int i = 0; i < sun.length; i++ ) {			
				rea += sun[i];
			
		}
		return name + " " + sun.length + "�Ƶ��ܳɼ���" + rea;
	}
}