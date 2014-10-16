package threading;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InsufficientResourcesException;

public class ThreadStartCheck {

    public static void main(String[] args) {
        System.out.println("before");
        Thread th = new Thread(new Runnable(){
            public void run() {
              try {
                Thread.sleep(2000);
                System.out.println("threaded");
              } catch (InterruptedException ex) {
                Logger.getLogger(ThreadStartCheck.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          
        });
        System.out.println("between");
        th.start();
        System.out.println("after");
    }

}
