//��ʾת���ַ���ʹ��
public class ChangeChar {

    //��дһ��main����
    public static void main(String[] args) {

        //\t ：一个制表位，实现对齐的功能
        System.out.println("北京\t 天津\t 上海");
        // \n ：换行符
        System.out.println("jack\nsmith\nmary");
        // \\ ：一个\ \\
        System.out.println("C:\\Windows\\System32\\cmd.exe");
        // \" :一个" System.out.println("老韩说:\"要好好学习 java,有前途\"");
        // \' ：一个' System.out.println("老韩说:\'要好好学习 java,有前途\'");
        // \r :一个回车 System.out.println("韩顺平教育\r 北京");
        // 解读
        // 1. 输出 韩顺平教育
        // 2. \r 表示回车
        System.out.println("韩顺平教育\r 北京"); // 北京平教育
    }

}
