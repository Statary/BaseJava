package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionsTry {

    public static void main(String[] args) {
        CollectionsTry ct = new CollectionsTry();
        ct.run();
    }

    private void run() {
        sortCollection();
    }

    private void sortCollection() {
        List<Integer> nums = new ArrayList<Integer>();
        Random rand = new Random();
        for( int i = 0; i < 10; i++ )
        {
            nums.add( rand.nextInt( 5) );
        }

        System.out.println(nums);
        long start = System.nanoTime();
        Collections.sort(nums);
        long end = System.nanoTime();
        System.out.println(nums);

        System.out.println((end-start)/1e9);
    }

}
