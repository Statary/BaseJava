package oop;

public class StaticClassCheck {

    int i = 0;
  
    public static void main(String[] args) {
//        new Child();
//        new A();
//        new B();
        new CnangingTest().foo();
    }
    
    static void run(){
//      System.out.println(String.valueOf(i));
    }
    
    class CheckCompiledClasses {
      public void run() {
        System.out.println("rruun");
      }
    }
    
    static class CnangingTest {
        private int instanceFieldB = fooInit("Class B instance field");
        private static int staticFieldB = fooInit("Class B static(class) field");

        public CnangingTest() {
            print("Class B constructor called");
            print( String.valueOf(staticFieldB));
            staticFieldB = 2;
            print( String.valueOf(staticFieldB));
        }
        void foo(){
            print( String.valueOf(staticFieldB));
            staticFieldB = 4;
            print( String.valueOf(staticFieldB));
        }
    }
    
    static class Parent {
        Parent() {
          System.out.println("1");
            foo();
        }
        void foo() {
          System.out.println("2");
            //empty method
        }
    }
    static class Child extends Parent {
        private String x = "hello-buddy";
        private int e = fooInit("Class Child instance field");
        
        public Child() {
            foo();
        }
        static {
            print("Initialization block of A is called");
        }
        
        @Override
        void foo() {
            System.out.println("3");
            System.out.println(x.length());
        }
    }
    
///////////////// new task /////////////////

    public static int fooInit(String who) {
        System.out.println(String.format("%s initialized", who));
        return 0;
    }
    public static void print(String what) {
        System.out.println(what);
    }
    public static class A {
        private int instanceFieldA = fooInit("Class A instance field");
        private static int staticFieldA = fooInit("Class A static(class) field");
        //начало экземплярного инициализационного блока
        {
            print("Initialization block of A is called");
        }
//        private int instanceFieldA = fooInit("Class A instance field");
        //начало статического инциализационного блока
        static {
            print("Static initialization block of A is called");
        }
        A() {
            print("Class A constructor called");
        }
    }
    
///////////////// new task /////////////////
    
    public static class B extends A {
        private int instanceFieldB = fooInit("Class B instance field");
        private static int staticFieldB = fooInit("Class B static(class) field");

        {
            print("Initialization block of B is called");
        }
        static {
            print("Static initialization block of B is called");
        }
        public B() {
            print("Class B constructor called");
        }
//        uncalled because it is init baby!
//        private void func() {
//            print("Class A dyn func()");
//        }
    }
    
}