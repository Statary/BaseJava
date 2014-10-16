package thread_collections;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

public class HolidayTask {
    
    private static int days = 5;
    private static final File file = new File("./src/data/holidays.txt");
    
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        CopyOnWriteArrayList<String> srcList = new CopyOnWriteArrayList( FileUtils.readLines(file, "UTF-8"));
        ConcurrentSkipListSet holidaySet = new ConcurrentSkipListSet();
        ConcurrentSkipListMap<String, String> holidayTypes = new ConcurrentSkipListMap<>();
        ConcurrentSkipListMap<Date, Integer> dailyHolidayCount = new ConcurrentSkipListMap<>();
        ConcurrentSkipListMap<Date, Integer> monthlyHolidayCount = new ConcurrentSkipListMap<>();
        
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService ex = Executors.newFixedThreadPool(2);

        // TODO: Automative counting parts of list.
        Future<Long> f1 = ex.submit( new HolidayRunner(0, 500, srcList, holidaySet
                                    , latch, holidayTypes, dailyHolidayCount, monthlyHolidayCount
                                    ));
        Future<Long> f2 = ex.submit( new HolidayRunner(500, 1000, srcList, holidaySet
                                    , latch, holidayTypes, dailyHolidayCount, monthlyHolidayCount
                                    ));
        Future<Long> f3 = ex.submit( new HolidayRunner(1000, srcList.size(), srcList, holidaySet
                                    , latch, holidayTypes, dailyHolidayCount, monthlyHolidayCount
                                    ));

        latch.countDown();
        ex.shutdown();
        
        // TODO: How to handle/detect when last thread finished????? CyclicBarrier? and with start?
        Thread.sleep(1000);
        
//        System.out.println("SIZE: " + holidaySet.size());
//        System.out.println("test SIZE: " + holidayTypes.size());
        //check RESULT ::
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

        // #3 - Soon
        System.out.println("Soon :");
        while ( days != 0)
        {
          cal.add(Calendar.DATE, 1);
          Holiday.printByDates(holidaySet, cal.getTime());
          days--;
        }
        
        // #4 - max and min holidayable day/date
        int max = Integer.MIN_VALUE;
        final List<Map.Entry< Date, Integer >> maxList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        final List<Map.Entry< Date, Integer >> minList = new ArrayList<>();

        for ( final Map.Entry< Date, Integer > entry : dailyHolidayCount.entrySet() ) {
            if ( max < entry.getValue() ) { 
                max = entry.getValue();
                maxList.clear();
            }
            if ( max == entry.getValue() )
                maxList.add( entry );
            
            if ( min > entry.getValue() ) { 
                min = entry.getValue();
                minList.clear();
            }
            if ( min == entry.getValue() )
                minList.add( entry );
        }
        System.out.println("Max Holidayable day : " + maxList);
        System.out.println("Min Holidayable days : " + minList);
        
        // #5 - holidays count in month
        System.out.println("Holiday count per month : " + monthlyHolidayCount);
        Integer sum = 0;
        for (Integer n : monthlyHolidayCount.values()) sum +=n;
        System.out.println("Must be total sum : " + holidaySet.size());
        System.out.println("Check total sum : " + sum);
        // TODO: Why expected and actual result not equals?
        // TODO: remove deprecated Date and use ? Calendar ? DateFormat ?

//        System.out.println(cal.getTime());

        System.out.println( holidaySet);
        
        ex.awaitTermination(6, TimeUnit.SECONDS);
    }

}
