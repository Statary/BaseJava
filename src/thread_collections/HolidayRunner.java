package thread_collections;

import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
    
public class HolidayRunner implements Callable<Long>{
    Integer start;
    Integer end;
    List<String> list;
    NavigableSet<Holiday> resultSet;
    CountDownLatch latch;
    ConcurrentSkipListMap<String, String> types;
    ConcurrentSkipListMap<Date, Integer> dailyHolidayCount;
    ConcurrentSkipListMap<Date, Integer> monthlyHolidayCount;

    public HolidayRunner(Integer start, Integer end, List<String> srcList
                        , NavigableSet<Holiday> resultSet, CountDownLatch latch
                        , ConcurrentSkipListMap<String, String> types
                        , ConcurrentSkipListMap<Date, Integer> dailyHolidayCount
                        , ConcurrentSkipListMap<Date, Integer> monthlyHolidayCount
                        ) {
      this.start = start;
      this.end = end;
      this.list = srcList;
      this.resultSet = resultSet;
      this.latch = latch;
      this.types = types;
      this.dailyHolidayCount = dailyHolidayCount;
      this.monthlyHolidayCount = monthlyHolidayCount;
    }
    //// !!! NOTE - new init is local var, but external intialized var is public.

    @Override
    public Long call() throws Exception {
        latch.await();

        long startTime = System.nanoTime();

        Holiday holiday;
        String str;
        Date date;
        Date month;
        String strHol;
        String strType;
        Integer delimierIndex;
        Integer countPerDay;
        Integer countPerMonth;
        for (int i = start; i < end; i++) {
            str = list.get(i);
            delimierIndex = str.indexOf(" ");
            date = new Date( str.substring(0, delimierIndex));
            strHol = str.substring( delimierIndex+1, str.lastIndexOf("(") - 1);
            strType = str.substring(str.lastIndexOf("(") + 1, str.length() - 1);
            types.put(strType, strType);
            strType = types.get(strType);
            holiday = new Holiday(date, strHol, strType);
            this.resultSet.add(holiday);
            
            countPerDay = dailyHolidayCount.get(date);
            dailyHolidayCount.put(date, (countPerDay==null?0:countPerDay) + 1);
            
            month = (Date) date.clone();
            month.setDate(1);
            countPerMonth = monthlyHolidayCount.get(month);
            monthlyHolidayCount.put(month, (countPerMonth==null?0:countPerMonth) + 1);
        }

//        System.out.println("DDDDDailyHolidayCount "+dailyHolidayCount);
        return System.nanoTime() - startTime;
    }
}
