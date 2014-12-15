package sandbox;


class A {
    void m1(A a) {
        System.out.println("A");
    }
//    void m1(B a) {
//        System.out.println("s");
//    }
}

class B extends A {
    void m1(B b) {
        System.out.println("B");
    }
}

class C extends B {
    void m1(B c) {
        System.out.println("C");
    }
}

class D {
    public static void main(String[] args) {
        A c1 = new C();
        c1.m1(new B());
        
        X1 x = new X1();
        System.out.println("FIN! OK!");
    }
}

// SOLVO TASK - unknown cortect given
class XX
{
  XX x1 = this;
}
class X1 extends XX
{
//  XX x1 = this;
}
class X2 extends X1
{
//  XX x1 = this;
}
