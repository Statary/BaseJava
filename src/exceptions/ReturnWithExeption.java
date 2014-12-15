package exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnWithExeption {

    public static void main(String[] args) {
        ReturnWithExeption rr = new ReturnWithExeption();
        rr.run();
    }

    private void run() {
//        String testRet = testReturn();
        String testRet = testReturn2();
        System.out.println(testRet);
    }

    private String testReturn() {
        int a, b = 0;
        String strRet = "asd";
        a = 2;
        try {
            int c = a / b;
            strRet = "New";
            System.out.println("after throwing");
        } catch (ArithmeticException ex) {
            System.out.println("catched");
            Logger.getLogger(ReturnWithExeption.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
        }
        System.out.println("operation after finnaly");
        return strRet;
    }

    private String testReturn2() {
        String str = null;
        try {
            str = callExc();
        } catch (ArithmeticException ex) {
            System.out.println("catched parent");
            Logger.getLogger(ReturnWithExeption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

    private String callExc() throws ArithmeticException {
        String strRet = "asd";
        int a, b = 0;
        a = 2;
        try {
            int c = a / b;
            strRet = "New";
            System.out.println("after throwing");
        } catch (ArithmeticException ex) {
            System.out.println("catched");
            Logger.getLogger(ReturnWithExeption.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            System.out.println("finally");
        }
        System.out.println("operation after fin");
        return strRet;
    }

}
