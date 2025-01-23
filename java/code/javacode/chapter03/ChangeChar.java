//演示转义字符的使用
public class ChangeChar {

	public static void main(String[] args) {
		// \t :相当于一个空格
		System.out.println("北京\t天京\t上海");
		// \n :换行符
		System.out.println("jack\nsmith\nmary");
		// \\ :一个\
		System.out.println("c:\\Windows\\System32\\cmd.exe");
		// \" :一个"
		System.out.println("老韩说：\"要好好学习\"");
		// \' :一个'
		System.out.println("老韩说：\'要好好学习\'");
		// \r :结果为北京平教育
		System.out.println("韩顺平教育\r北京");

		
	}
}