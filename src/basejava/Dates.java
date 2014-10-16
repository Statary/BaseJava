package basejava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dates {

    public static void main(String[] args) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        System.out.println(cal.getTime());
        // Ouput "Wed Sep 26 14:23:28 EST 2012"

        String formatted = format1.format(cal.getTime());
        System.out.println(formatted);
        // Output "2012-09-26"

        System.out.println(format1.parse(formatted));
        // Output "Wed Sep 26 00:00:00 EST 2012"
    }

}
