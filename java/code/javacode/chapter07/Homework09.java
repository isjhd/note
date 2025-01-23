
public class Homework09 {
	

	public static void main(String[] args) {

		Music music = new Music("打上花火", 4.49);
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
		System.out.println("播放" + name + "时常" + times);
	}

	public String getInfo () {
		return "播放歌曲的名字" + name + "时常为" + times;
	}
}