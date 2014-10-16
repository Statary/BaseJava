package sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NavigableMap;

public class ByRefOrValue {

    public static void main(String[] args)
    {
        ByRefOrValue me = new ByRefOrValue();
        me.run();
    }

    public void run()
    {
        // ArrayList checks
        ArrayList<String> arList = new ArrayList<>();
        arList.add("qwe0");
        arList.add("qwe1");
        arList.add("qwe2");
        System.out.println(arList.toString());
        checkArList(arList);
        System.out.println(arList.toString());
        
        // Map checks
        HashMap<String,String> map = new HashMap<>();
        map.put("1", "qwe0");
        map.put("2", "qwe1");
        map.put("3", "qwe2");
        System.out.println(map.toString());
        checkMap(map);
        System.out.println(map.toString());
      
        // int
        int num = 5;
        System.out.println(num);
        changeInt(num);
        System.out.println(num);
        
        // Integer
        Integer num2 = 5;
        System.out.println(num2);
        changeInteger(num2);
        System.out.println(num2);
        
        // String
        String str = "old str";
        System.out.println(str);
        changeString(str);
        System.out.println(str);
        
        // Array of String
        String[] arStr = new String[3];
        arStr[0] = "qwe0";
        arStr[1] = "qwe1";
        arStr[2] = "qwe2";
        System.out.println(Arrays.deepToString(arStr));
        changeArStr(arStr);
        System.out.println(Arrays.deepToString(arStr));
        
        // My Object
        Obj o = new Obj("old");
        System.out.println(o.str);
        changeObj(o);
        System.out.println(o.str);
    }
    
    private void changeInt(int nnum)
    {
      nnum = 10;
    }
    private void changeInteger(Integer nnum2)
    {
      nnum2 = 11;
    }
    private void changeString(String sstr)
    {
      sstr = "new str";
    }
    private void changeArStr(String[] aarStr)
    {
      aarStr[0] = "new it";
    }

    private void checkArList(ArrayList<String> arList) {
      arList.set(1, "new Str");
    }

    private void checkMap(HashMap<String, String> map) {
      map.put("2", "newwww");
      map.put("55", "sssss");
    }

    private void changeObj(Obj o) {
      o.str = "neeew";
    }
    
    class Obj {
      public String str;
      public Obj(String str) {
        this.str = str;
      }
    }
}
