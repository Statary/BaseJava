package threading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.naming.InsufficientResourcesException;
import static threading.Operations.WAIT_SEC;

public class Transfer implements Callable<Boolean>{
//  public static long WAIT_SEC = 2;
  private Account acFrom;
  private Account acTo;
  private int amount;
  private int id;
  private CountDownLatch startLatch;

  public Transfer(int id, Account acFrom, Account acTo, int amount, CountDownLatch startLatch) {
    this.acFrom = acFrom;
    this.acTo = acTo;
    this.amount = amount;
    this.id = id;
    this.startLatch = startLatch;
  }
  
  public Transfer(int id, Account acFrom, Account acTo, int amount) {
    this.acFrom = acFrom;
    this.acTo = acTo;
    this.amount = amount;
    this.id = id;
  }

  @Override
  public Boolean call() throws Exception {
//    System.out.println("calls transfer id " + id);
    System.out.println("waiting to start " + id);
    startLatch.await();
    System.out.println("failed transfers" + acFrom.getFailCounter());
    if ( acFrom.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)){
        try {
//            System.out.println("acFrom lock");
            if (acFrom.getBalance() < amount)
                throw new InsufficientResourcesException();
            if ( acTo.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)){
                try {
//                    System.out.println("acTo lock");
                    acFrom.withdraw(amount);
                    acTo.deposit(amount);
                    Thread.sleep( 1000*(new Random()).nextInt(5));
                } finally {
                    acTo.getLock().unlock();
//                    System.out.println("acTo unlock");
                }
            }
            else
            {
                acTo.incFailTransferCount();
                System.out.println("sorry. acTo was locked.");
                return false;
            }
        } finally {
            acFrom.getLock().unlock();
//            System.out.println("acFrom unlock");
        }
        System.out.println("success. transferred " + amount);
        return true;
    }
    else
    {
        acFrom.incFailTransferCount();
        System.out.println("sorry. acFrom was locked.");
        return false;
    }
  }
    
}
