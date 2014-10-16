package thread_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CopyOnWriteArrL {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Integer> l1 = Collections.synchronizedList(new ArrayList<Integer>());
        List<Integer> l2 = new CopyOnWriteArrayList<>();
        
        fillList(l1,100);
        fillList(l2,100);
        
        System.out.println("List Synced: ");
        checkList(l1);
        
        System.out.println("CopyOnWriteArrayList: ");
        checkList(l2);
    }

  private static void fillList(List<Integer> list, int len) {
      for (int i = 0; i < len; i++) {
          list.add(i+1);
      }
  }

  private static void checkList(List<Integer> list) throws InterruptedException, ExecutionException {
      CountDownLatch latch = new CountDownLatch(1);
      ExecutorService ex = Executors.newFixedThreadPool(2);
      
      Future<Long> f1 = ex.submit( new ListRunner(0,50,list,latch));
      Future<Long> f2 = ex.submit( new ListRunner(50,100,list,latch));
      
      latch.countDown();
      System.out.println("thread 1: "+ f1.get()/1000);
      System.out.println("thread 2: "+ f2.get()/1000);
  }

  static class ListRunner implements Callable<Long>{
    List list;
    Integer start;
    Integer end;
    CountDownLatch latch;

    public ListRunner(Integer start, Integer end, List list, CountDownLatch latch) {
      this.list = list;
      this.start = start;
      this.end = end;
      this.latch = latch;
    }
    
    @Override
    public Long call() throws Exception {
        latch.await();
        
        long startTime = System.nanoTime();
        for (int i = start; i < end; i++) {
            list.get(i);
            if ( i%10 == 0)
              list.add(222*i);
        }
        return System.nanoTime() - startTime;
    }
  }
}
