package basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Generics {

  public void run()
  {
    
  }
  private class Product implements Comparable<Product>
  {

    @Override
    public int compareTo(Product o) {
      
      return 0;
    }
    
  }
  
  public void run4()
  {
    List<String> strList = new ArrayList<>();
    List<Object> objList = new ArrayList<>();
    print(strList);
//    print(objList);
  }
  void print( Collection<String> list)
  {
    for (String string : list) {
      System.out.println(string);
    }
  }
  
  public void run3()
  {
//    SomeType someType = new SomeType();
    SomeType<?> someType = new SomeType<Object>();
    List<String> list = Arrays.asList("value");
    someType.test(list);
  }
  private class SomeType<T> {
    
    public <E> void test( Collection<E> coll)
    {
      for (E e : coll) {
        System.out.println("asd");
        System.out.println(e);
      }
    }
    
    public void test( List<Integer> integerList)
    {
      for (Integer integer : integerList) {
        System.out.println(integer);
      }
    }
  }
  
  public void run2()
  {
    List badList = new ArrayList();
    List<String> goodList = new ArrayList<>();
    
    badList = goodList;
    badList.add(8);
    
    String s = (String) badList.get(0);
    
    System.out.println(badList.toString());
    System.out.println(goodList.toString());
  }
}
