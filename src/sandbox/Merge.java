package sandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Merge {

    private static final File file1 = new File("./src/data/file1.txt");
    private static final File file2 = new File("./src/data/file2.txt");
    
    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();
        try {
          BufferedReader in = new BufferedReader( new FileReader( file1));
          String str;
          while (( str = in.readLine()) != null) {
            list.add(str);
          }
          in.close();

          in = new BufferedReader( new FileReader( file2));
          String str2;
          ListIterator<String> it = list.listIterator();
              while (( str2 = in.readLine()) != null) {
                  String str1 = it.next();
                  int diff = strcmp(str1, str2);
                  if ( diff == 0){
                      it.next();
                  }else if ( diff == -1){
                      it.add(str2);
                  }else if ( diff == 1){
                      while (it.hasNext() && strcmp(str1, str2) == 1)
                          it.next();
                      it.add(str2);
                  }
                  if (!it.hasNext())
                    return;
          }
          in.close();
        } catch (IOException ex) {
//          Logger.getLogger...;
        }
    }
    
    private static int strcmp( String first, String second) {
      return 0;
      //Для сравнения строк можно использовать функцию strcmp, которая возвращает 0, если строки равны,
      //-1, если первая строка меньше, чем вторая, и 1, если первая строка больше, чем вторая.
    }
  
}
