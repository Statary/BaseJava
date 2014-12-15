package patterns;

import java.util.Arrays;
import java.util.List;

public class AdapterPattern {

    public static void main(String[] args) {
        AdapterPattern adap = new AdapterPattern();
        adap.run();
    }

    private void run() {

    }
}

//class X {
//    public void meth(){
//        System.out.println("AAA");
//    }
//    protected void OnClick() {
//        action.execute();
//    }
//}
//
//class Y {
//    public void meth(){
//        System.out.println("BBB");
//    }
//    protected void OnClick() {
//        action.execute();
//    }
//}
//
//class Action {
//    private List<Object> controls;
//    public void SetControls(Object[] conts) {
//        this.controls = Arrays.asList(conts);
//    }
//    public void execute(){
//        meth();
//    }
//    public void meth() {
//        for(Object adapter : controls) {
//            if (adapter instanceof X)
//                ((X)adapter).meth();
//            else if (adapter instanceof Y)
//                ((Y)adapter).meth();
//        }
//    }
//}

//class Adapter implements Enablable{
//    private Object object;
//    public Adapter(Object object)
//    {
//        this.object = object;
//    }
//    public void meth()
//    {
//        ((X)object).meth();
//    }
//}
//
//interface Enablable{
//    public void meth();
//}