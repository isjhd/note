
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {
        Test t = new Test();
        t.change(15);
        System.out.println(t.a);
    }
}

class Test {
    int a = 10;
    public void change(int a){
        a = 15;
    }
}