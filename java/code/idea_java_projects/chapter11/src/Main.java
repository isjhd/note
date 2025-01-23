import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        new View().mainMenu();
    }
}
class View {
    private boolean loop = true;
    private char key;
    private boolean over = true;
    private boolean shi = true;
    private String zhang;
    private String mi;
    private String hao;
    private String ma;
    Scanner myScanner = new Scanner(System.in);

    public void setRegister() {
        System.out.print("请输入账号：");
        zhang = myScanner.next();
        System.out.print("请输入密码：");
        mi = myScanner.next();
        System.out.println("输入成功...");
    }


    public void logOn() {
        if (zhang != null) {
            do {
                System.out.print("请输入账号：");
                hao = myScanner.next();
                System.out.print("请输入密码：");
                ma = myScanner.next();
                if (hao.equals(zhang) && mi.equals(ma)) {
                    System.out.println("登录成功...");
                    over = false;
                } else {
                    System.out.println("密码或账号输入错误...");
                    do {
                        System.out.print("是否重新输入(y/n)：");
                        key = myScanner.next().charAt(0);
                        if (key == 'y') {
                            System.out.println("请重新输入");
                            shi = false;
                        } else if (key == 'n') {
                            System.out.println("退出...");
                            over = false;
                            shi = false;
                        } else {
                            System.out.println("输入有误...");
                            shi = true;
                        }
                    } while (shi);
                }
            } while (over);
        } else {
            System.out.println("请先注册...");
        }
    }

    public void output() {
        if (zhang != null && hao != null) {
            System.out.println("账号：" + hao);
            System.out.println("密码：" + ma);
        } else {
            System.out.println("请先注册或登录...");
        }
    }

    public void mainMenu() {
        do {
            System.out.println("\n==============登录页面===============");
            System.out.println("\t\t\t1、注册");
            System.out.println("\t\t\t2、登录");
            System.out.println("\t\t\t3、输出");
            System.out.println("\t\t\t4、退出");

            System.out.print("请输入您的选择(1~4): ");
            key = myScanner.next().charAt (0);
            switch (key) {
                case '1':
                    setRegister();
                    break;
                case '2':
                    logOn();
                    break;
                case '3':
                    output();
                    break;
                case '4':
                    loop = false;
                    break;
            }
        } while(loop);
        System.out.println("你退出了登录系统...");
    }
}
