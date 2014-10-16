package threading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Account {
    private volatile int balance;
    private Lock l;
    private AtomicInteger failCounter = new AtomicInteger(0);
    
    public int getBalance() {
      return balance;
    }

    public Account(int initBalance) {
      this.balance = initBalance;
      l = new ReentrantLock();
    }
    
    public void incFailTransferCount(){
        failCounter.incrementAndGet();
    }

    public int getFailCounter() {
        return failCounter.get();
    }
    
    public Lock getLock(){
      return l;
    }
    
    public void withdraw(int amount){
      balance -= amount;
      System.out.println("withdraw success");
    }

    public void deposit(int amount){
      balance += amount;
      System.out.println("deposit success");
    }
    
}
