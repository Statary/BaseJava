package basejava;

import java.util.Arrays;

public class CheckArray {

  void run() {
    String[] srcAr = new String[4];
    String[] tmpAr = srcAr;
    String[] resAr = new String[7];
    for (Integer i = 0; i < srcAr.length; i++) {
      srcAr[i] = "asd"+i.toString();
    }
    
    // #1
    System.arraycopy(srcAr, 0, resAr, 0, 4);
    
    // #2
    resAr[1] = "bb";
    System.out.println("#2. src:"+Arrays.deepToString(srcAr));
    
    // #3
    resAr[4] = "ccc";
    System.out.println("#3. res:"+Arrays.deepToString(resAr));
    
    // #4
    srcAr[2] = "ee";
    srcAr = resAr;
    System.out.println("#4. src:"+Arrays.deepToString(srcAr));
    
    // #5
    Arrays.fill(tmpAr, "sees");
    System.out.println("#5. tmp:"+Arrays.deepToString(tmpAr));
    
    // #6
    resAr = Arrays.copyOf(resAr, 5);
    System.out.println("#6. res:"+Arrays.deepToString(resAr));
    
    // #6
    resAr = Arrays.copyOfRange(resAr, 0, 2);
    System.out.println("#7. res:"+Arrays.deepToString(resAr));
    
  }
}
