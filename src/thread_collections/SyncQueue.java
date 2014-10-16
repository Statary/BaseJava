package thread_collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncQueue {

    public static void main(String[] args) {
        final SynchronousQueue<String> qu = new SynchronousQueue();
        
        new Thread(new Runnable(){
            public void run() {
              for (int i = 0; i < 3; i++) {
                try {
                    String str = "sss"+i*3;
                    System.out.println("bef sl putting... str="+str);
                    Thread.sleep(1000);
                    System.out.println("aft sl putting... str="+str);
                    qu.put(str);
                    System.out.println("putting... str="+str);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
            }
          
        }).start();
        new Thread(new Runnable(){
            public void run() {
              for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("bef sl taking... i="+i);
                    Thread.sleep(2000);
                    System.out.println("aft sl taking... i="+i);
                    String str = qu.take();
                    System.out.println("taking... str="+str);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
            }
          
        }).start();
    }

}
