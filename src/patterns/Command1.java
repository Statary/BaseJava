package patterns;

// in this example, suppose you use a switch to control computer

/* The Invoker class */
//class Switch {
////    private List<Command> history = new ArrayList<Command>();
//
//    public Switch() {
//    }
//
//    public void storeAndExecute(ShutDownCommand command) {
////        this.history.add(command); // optional, can do the execute only!
//        command.execute();
//    }
//}

class XXX {
    public void meth(){
        System.out.println("XXX");
    }
    protected void OnClick(ShutDownCommand action) {
        action.execute();
    }
}

class YYY {
    public void meth(){
        System.out.println("YYY");
    }
    protected void OnClick(ShutDownCommand action) {
        action.execute();
    }
}

/* The Receiver class */
class Computer {

    public void shutDown() {
        System.out.println("computer is shut down");
    }
}

/* The Command for shutting down the computer*/
class ShutDownCommand{ //<T extends Enablable> {
    private Enablable[] controls;
    public void SetControls(Enablable[] controls) {
        this.controls = controls;
    }

    public void execute(){
        System.out.println("command::execute()");
        for (Enablable object : controls) {
            object.meth();
        }
    }
}

interface Enablable{
    public void meth();
}

class AdaptXXX implements Enablable{
    private XXX adaptee = new XXX();
    public void meth(){
        adaptee.meth();
    }
}

class AdaptYYY implements Enablable{
    private YYY adaptee = new YYY();
    public void meth(){
        adaptee.meth();
    }
}

/* The client */
public class Command1 {
    public static void main(String[] args){
        AdaptXXX xxx = new AdaptXXX();
        AdaptYYY yyy = new AdaptYYY();
        Enablable[] controls = {xxx,yyy};
        
        ShutDownCommand shutdown = new ShutDownCommand();
        shutdown.SetControls(controls);
        
//        xxx.OnClick(shutdown);
    }
}