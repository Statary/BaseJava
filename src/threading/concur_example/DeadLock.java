package threading.concur_example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeadLock {

    public static void main(String[] args) {
        
      DeadLock dl = new DeadLock();
//      dl.runDLSimple();
//      dl.runDLSimpleResolved();
//      dl.runDLAdvanced();
      dl.runDLAdvancedResolved();
    }

    // calls simple deadlock
    private void runDLSimple() {
        final A a = new A("a");
        final A b = new A("b");
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            synchronized (a){
              try {
                Thread.sleep(500);
              } catch (InterruptedException ex) {
                Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
              }
              synchronized (b){
                System.out.println("runDLSimple 1 process");
              }
            }
            System.out.println("runDLSimple 1 complete");
          }
        })
        .start();
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            synchronized (b){
              synchronized (a){
                System.out.println("runDLSimple 2 process");
              }
            }
            System.out.println("runDLSimple 2 complete");
          }
        })
        .start();
    }
    
    // resolve simple deadlock by operations rules and priorities
    private void runDLSimpleResolved() {
        final A a = new A("a");
        final A b = new A("b");
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            synchronized (a){
              try {
                Thread.sleep(500);
              } catch (InterruptedException ex) {
                Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
              }
              synchronized (b){
                System.out.println("runDLSimpleResolved " + this.getClass().getName() + " process");
              }
            }
            System.out.println("runDLSimpleResolved " + this.getClass().getName() + " complete");
          }
        })
        .start();
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            synchronized (a){
              synchronized (b){
                System.out.println("runDLSimpleResolved " + this.getClass().getName() + " process");
              }
            }
            System.out.println("runDLSimpleResolved " + this.getClass().getName() + " complete");
          }
        })
        .start();
    }
    
    // calls advanced deadlock
    private void runDLAdvanced() {
        final A a = new A("a");
        final A b = new A("b");
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            advanceDL(a,b,"1");
          }
        })
        .start();
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            advanceDL(b,a,"2");
          }
        })
        .start();
    }

    // help method
    private void advanceDL(A a, A b, String id) {
        synchronized (a){
            try {
              Thread.sleep(500);
            } catch (InterruptedException ex) {
              Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized (b){
              System.out.println("advanceDL " + id + " process");
            }
          }
          System.out.println("advanceDL " + id + " complete");
    }

    // resolve advanced deadlock by ReentrantLock
    private void runDLAdvancedResolved() {
        final A a = new A("a");
        final A b = new A("b");
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              advanceResolvedDL(a,b,"1");
            } catch (InterruptedException ex) {
              Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        })
        .start();
        
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              advanceResolvedDL(b,a,"2");
            } catch (InterruptedException ex) {
              Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        })
        .start();
    }

    // help method
    private void advanceResolvedDL(A a, A b, String id) throws InterruptedException{
        if ( a.getLock().tryLock(2,TimeUnit.SECONDS)){
            Thread.sleep(500);
            if ( b.getLock().tryLock(2,TimeUnit.SECONDS)){
              try{
                System.out.println("advanceResolvedDL " + id + " process");
              }
              finally {
                b.getLock().unlock();
              }
            }
        }
        System.out.println("advanceResolvedDL " + id + " complete");
    }
}

class A {
  private String name;
  private Lock l;
  
  public A(String name) {
    this.name = name;
    this.l = new ReentrantLock();
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Lock getLock() {
    return l;
  }
}