package basejava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread_collections.Holiday;


public class CheckCollections {

  void run4() {
    System.out.println("__1__");
    Queue<Integer> q = new LinkedList<>();
    for (int i = 5; i > 0; i--) {
      q.add(i);
    }
    while ( !q.isEmpty())
      System.out.println(q.poll());
    
    System.out.println("__2__");
    Queue<Integer> pq = new PriorityQueue<>();
    for (int i = 5; i > 0; i--) {
      pq.add(i);
    }
    while ( !pq.isEmpty())
      System.out.println(pq.poll());
    
    System.out.println("__3__");
    Queue<Integer> myq = new PriorityQueue<>(5,new Comparator<Integer>() {

      @Override
      public int compare(Integer o1, Integer o2) {
        if ( o1%2 == 0)
          return -1;
        else
          return 1;
      }
    });
    myq.add(5);
    myq.add(2);
    myq.add(1);
    myq.add(4);
    while ( !myq.isEmpty())
      System.out.println(myq.poll());
  }
//  class MyPriorQueue extends PriorityQueue<Integer> implements comp
  
  void run3()
  {
//    HashMap<Integer, String> hp = new HashMap<>();
//    HashMap<Integer, String> hp = new LinkedHashMap<>();
    HashMap<Integer, String> hp = new LinkedHashMap<>(3, 1, true);
    hp.put(8, "dd");
    hp.put(2, "bb");
    hp.put(4, "cc");
    hp.get(8);
    System.out.println(hp);
    
    LinkedHashMap<Integer, String> lru = new SimleLRUCache<>(2);
    lru.put(1, "1");
    lru.put(2, "2");
    lru.put(3, "3");
    lru.get(2);
    lru.put(9, "9");
    System.out.println(lru);
    
    Map<Data, String> map = new WeakHashMap<Data, String>();
    Data data = new Data();
    map.put(data, "inf");
    
    data = null;
    System.gc();
    for (int i = 0; i < 10000; i++) {
      if (map.isEmpty())
      {
        System.out.println(i+"Empty!");
        break;
      }
    }
//    System.out.println("sss");
  }

  private static class Data {

    public Data() {
    }
  }
  public class SimleLRUCache<K,V> extends LinkedHashMap<K, V>
  {
    private final int capacity;

    public SimleLRUCache(int capacity) {
      super(capacity + 1, 1.1f, true);
      this.capacity = capacity;
    }

//    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
      return this.size() > capacity;
    }
  }
  
  private static final File file = new File("./src/data/holidays.txt");
  private int days = 5;
  
  void run() {
    Holiday holiday = null;
    NavigableSet<Holiday> holidaySet = new TreeSet<>();
    Map<String, String> types = new WeakHashMap<String, String>();
    try {
      BufferedReader in = new BufferedReader( new FileReader( file));
      String str;
      String date;
      String strHol;
      String strType;
      Integer delimierIndex;
      while (( str = in.readLine()) != null) {
        delimierIndex = str.indexOf(" ");
        date = str.substring(0, delimierIndex);
        strHol = str.substring( delimierIndex+1, str.lastIndexOf("(") - 1);
        strType = str.substring(str.lastIndexOf("(") + 1, str.length() - 1);
        types.put(strType, strType);
        strType = types.get(strType);
        holiday = new Holiday(date, strHol, strType);
        holidaySet.add(holiday);
      }
      in.close();
    } catch (IOException ex) {
      Logger.getLogger(CheckCollections.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println(types.size());
    
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.clear(Calendar.MINUTE);
    cal.clear(Calendar.SECOND);
    cal.clear(Calendar.MILLISECOND);
    
    // #1 - today
    System.out.println("Today :");
    Holiday.printByDates(holidaySet, cal.getTime());
    
    // #2 - tomorrow
    System.out.println("Tomorrow :");
    cal.add(Calendar.DATE, 1);
    Holiday.printByDates(holidaySet, cal.getTime());
    
    // #2 - Soon
    System.out.println("Soon :");
    while ( days != 0)
    {
      cal.add(Calendar.DATE, 1);
      Holiday.printByDates(holidaySet, cal.getTime());
      days--;
    }
    
    System.out.println(cal.getTime());
    
    System.out.println( holidaySet);
  }
  
  void run2() {

    // Lists:
    System.out.println("Lists:");
    
    Collection<Integer> num = new LinkedList<>();
    for (int i = 0; i < 3; i++) {
      num.add(i*5);
    }
    System.out.println("#1 "+num.toString());
    
    num.remove(5);
    System.out.println("#2 "+num.toString());
    
    System.out.println("#3 num.contains(10) = "+num.contains(10));
    
    num.add(22);
    num.add(33);
    System.out.println("#4 "+num.toString());
    for (Integer integer : num) {
      System.out.print(integer.toString()+";");
//      num.remove(22); // cought Exception here
    }
    System.out.println("");
    System.out.println("#4 "+num.toString());
    
    
    System.out.println("#5 "+num.toString());
    for (Iterator<Integer> it = num.iterator(); it.hasNext();) {
      Integer integer = it.next();
      
    }
    System.out.println("#5 "+num.toString());
    
    LinkedList<Integer> linkedList = new LinkedList<>();
    ArrayList<Integer> arrayList = new ArrayList<>();
//    linkedList.
//    arrayList.
    
    // Sets:
    System.out.println("Sets:");
    
    SortedSet<Integer> set = new TreeSet<>(new Comparator<Integer>(){
      public int compare(Integer o1, Integer o2) {
        return 0;
      }
    });
    set.add(1);
    set.add(2);
    set.add(1);
    System.out.println(set);
    
    SortedSet<Integer> sortedSet = new TreeSet<>();
    NavigableSet<Integer> navSet = new TreeSet<>();
    for (int i = 0; i < 10; i++) {
      sortedSet.add(i+1);
      navSet.add(i+1);
    }
    for (Integer ii : sortedSet) {
//      System.out.println("hash:"+ii.hashCode());
    }
    sortedSet = sortedSet.tailSet(5);
    System.out.println(sortedSet.first());
    System.out.println(navSet.lower( 5));
    System.out.println(navSet.higher(5));
    System.out.println(sortedSet.toString());
    
//    Stack
    HashSet<Integer> hashSet = new HashSet<>();
    TreeSet<Integer> treeSet = new TreeSet<>();
    HashMap<String, String> hashMap = new HashMap<>();
    TreeMap<String, String> treeMap = new TreeMap<>();
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
    for (int i = 0; i < 10; i++) {
      linkedHashSet.add(i*2+1);
    }
    for (Integer iii : linkedHashSet) {
      System.out.println("hash:"+iii.hashCode());
    }
//    hashSet.
//    treeSet.
//    linkedHashSet.
//    treeMap.
//      hashMap.hashCode()

    
    SortedSet<String> intset = new TreeSet<>();
    intset.add("Россия");
    intset.add("Франция");
    intset.add("Гондурас");
    intset.add("Кот-Д'Ивуар");
    
    System.out.println(intset.toString());
    
  }
}
