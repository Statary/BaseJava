package threading;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InsufficientResourcesException;


public class Operations {
  public static long WAIT_SEC = 3;
  
    public static void main(String[] args) throws InsufficientResourcesException, InterruptedException {
        final Account a = new Account(1000);
        final Account b = new Account(2000);
        
        new Thread(new Runnable(){
            public void run() {
              try {
                transfer( a, b, 500);
              } catch (InsufficientResourcesException | InterruptedException ex) {
                Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          
        }).start();
        transfer( b, a, 300);
    }

    
  private static void transfer(Account a, Account b, int amount) throws InsufficientResourcesException, InterruptedException {
    if (a.getBalance() < amount)
        throw new InsufficientResourcesException();
    if ( a.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)){
        try {
            System.out.println("a lock");
            if ( b.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)){
                try {
                    System.out.println("b lock");
                    a.withdraw(amount);
                    b.deposit(amount);
                } finally {
                    b.getLock().unlock();
                    System.out.println("b unlock");
                }
            
            }
            else
            {
                b.incFailTransferCount();
                System.out.println("sorry. b was locked.");
            }
        } finally {
            a.getLock().unlock();
            System.out.println("a unlock");
        }
        System.out.println("trunsfer success");
    }
    else
    {
        a.incFailTransferCount();
        System.out.println("sorry. a was locked.");
    }
  }

//  private static void transfer2(Account a, Account b, int amount) throws InsufficientResourcesException {
//    if (a.getBalance() < amount)
//        throw new InsufficientResourcesException();
//    synchronized (a){
//      System.out.println("a lock");
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//          Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        synchronized (b){
//      System.out.println("b lock");
//            a.withdraw(amount);
//            b.deposit(amount);
//        }
//    }
//    System.out.println("trunsfer success");
//  }
}