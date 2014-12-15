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
                    System.out.println("bef sleep Putting... str="+str);
                    Thread.sleep(1000);
                    System.out.println("aft sleep Putting... str="+str);
                    qu.put(str);
                    System.out.println("after Putting... str="+str);
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
                    System.out.println("bef sleep Taking... i="+i);
                    Thread.sleep(2000);
                    System.out.println("aft sleep Taking... i="+i);
                    String str = qu.take();
                    System.out.println("after Taking... str="+str);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
            }
          
        }).start();
    }

}
