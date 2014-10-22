package basejava;

class A {
  public A() {
    System.out.println("Construct A");
  }
  public void test1(){
    System.out.println("Test A");
  }
}
class B extends A {
  public B() {
    System.out.println("Construct B");
  }
  public void test1(){
    System.out.println("Test B");
  }
}

public class OOP_inherit {

  public static void main(String[] args) {
      // TODO code application logic here
    OOP_inherit oop = new OOP_inherit();
    oop.run();
  }

  private void run() {
    // #1
//    A a = new B();
//    a.test1();
//    B b = new B();
//    b.test1();
    
    // #2
    RealClass2 real = new RealClass2();
    real.foo();
  }
}

class MyClass {
  public void foo(){}
}
abstract class AbstrClass extends MyClass {
}
class RealClass1 extends AbstrClass {
  public void foo(int i){}
}
class RealClass2 extends AbstrClass implements Interf{
  public void foo(){}
}
interface Interf {
  public void foo();
}
