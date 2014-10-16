package threading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InsufficientResourcesException;


public class Operations2 {
  public static long WAIT_SEC = 2;
  
    public static void main(String[] args) {
        final Account a = new Account(2100);
        final Account b = new Account(2000);
        List arFuture = new ArrayList();
        
        ScheduledExecutorService sheduler = Executors.newSingleThreadScheduledExecutor();
        sheduler.scheduleAtFixedRate(new Runnable() {
          @Override
          public void run() {
            System.out.println("AAfailed transfers:" + a.getFailCounter());
            System.out.println("BBfailed transfers:" + b.getFailCounter());
          }
        }, 1, 1, TimeUnit.SECONDS);
        
        ExecutorService service = Executors.newFixedThreadPool(3);
        Random rnd = new Random();
        
        for (int i = 0; i < 10; i++) {
          Future f = service.submit( new Transfer(i, a, b, rnd.nextInt(400)));
          try {
            arFuture.add(f.get());
          } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Operations2.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        service.shutdown();
        System.out.println("RESULT ARRAY : "+arFuture.toString());
        
        try {
          service.awaitTermination(WAIT_SEC*20, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
          Logger.getLogger(Operations2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}