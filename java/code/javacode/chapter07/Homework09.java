
public class Homework09 {
	

	public static void main(String[] args) {

		Music music = new Music("���ϻ���", 4.49);
		music.play();
		System.out.println(music.getInfo());
	}
}
class Music {
	String name;
	double times;
	public Music (String name, double times) {
		this.name = name;
		this.times = times;
	}

	public void play () {
		System.out.println("����" + name + "ʱ��" + times);
	}

	public String getInfo () {
		return "���Ÿ���������" + name + "ʱ��Ϊ" + times;
	}
}