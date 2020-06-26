package xyz.gabear.learn.javase.exercise;


class A {
    A(int i) {
        System.out.println(i);
    }
}

class B {
    A a1 = new A(1);

    B() {
        System.out.println("h()");
        a3 = new A(2);
    }

    A a2 = new A(3);

    void f() {
        System.out.println("fun()");
    }

    A a3 = new A(4);
}

public class MyTest {
    public static void main(String[] args) {
        B b = new B();
        b.f();
    }
}

/*
输出如下：

1
3
4
h()
2
fun()

*/