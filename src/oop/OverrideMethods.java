package oop;

/**
 * Created by Asus on 15.12.2014.
 */
public class OverrideMethods {

    public static void main(String[] args) {
        BB.p();
        AA bb = new BB();
        System.out.println(bb.i);
        bb.p2();
        int i = 010;
        System.out.println(i);
    }
}


class BB extends AA{
    public int i = 10;
    public void p2(){
        System.out.println("BB");
    }
    public static void p(){
        System.out.println("BB");
    }
}
class AA{
    public int i = 12;
    public void p2(){
        System.out.println("AA");
    }
    public static void p(){
        System.out.println("AA");
    }
}