package oop.casting;

public class InstanceOfCheck {

    public static void main(String[] args) {
      InstanceOfCheck ins = new InstanceOfCheck();
      ins.run();
    }
    
    public void run(){
        A a = new A();
        B b = new B();
        C c = new C();
        
        if ( b instanceof A)
          System.out.println("b instanceof A");
        if ( b instanceof B)
          System.out.println("b instanceof B");
        if ( b instanceof C)
          System.out.println("b instanceof C");
        
        if ( b instanceof IB)
          System.out.println("b instanceof IB");
        if ( b instanceof IA)
          System.out.println("b instanceof IA");
        if ( b instanceof IC)
          System.out.println("b instanceof IC");
        check(b);
    }

    private void check(A a)
    {
        B checker = (B)a;
        if ( checker instanceof A)
            System.out.println("checker instanceof A");
        if ( checker instanceof B)
            System.out.println("checker instanceof B");
        if ( checker instanceof C)
            System.out.println("checker instanceof C");

        if ( checker instanceof IB)
            System.out.println("checker instanceof IB");
        if ( checker instanceof IA)
            System.out.println("checker instanceof IA");
        if ( checker instanceof IC)
            System.out.println("checker instanceof IC");
    }
}

class A {}
class B extends A implements IB{}
class C extends B {}

interface IA {}
interface IB extends IA {}
interface IC extends IB {}
