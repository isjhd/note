public class Main {
    public static void main(String[] args){

        B b = new B();
        b.walk();
    }
}

class A {
    public void run() {
        System.out.println("父类的run");
    }

    public void walk() {
        System.out.println("walk");
    }
}

class B extends A{
    public void run() {
        System.out.println("子类run");
    }

    public void sleep() {
        System.out.println("sleep");
    }
}