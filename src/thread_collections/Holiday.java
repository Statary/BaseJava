package thread_collections;

import java.util.Date;
import java.util.Set;

  public class Holiday implements Comparable<Holiday>
  {
    private Date date;
    private String name;
    private String type;

    public Holiday(String date, String name, String type) {
      this.date = new Date( date);
      this.name = name;
      this.type = type;
    }
    public Holiday(Date date, String name, String type) {
      this.date = date;
      this.name = name;
      this.type = type;
    }

    public Date getDate() {
      return date;
    }
    public void setDate(Date date) {
      this.date = date;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    @Override
    public int compareTo(Holiday o) {
      Date d = o.getDate();
      String n = o.getName();
      if ( d.equals( date))
      {
        if ( n.equals(name))
          return 0;
        else
          return name.compareTo(n);
      }
      else
      {
        return date.compareTo(d);
      }
    }

    @Override
    public String toString() {
      return "Holiday{" + "date=" + date + ", name=" + name + ", type=" + type + '}';
    }
    
    public static void printByDates( Set<Holiday> holidaySet, Date today)
    {
      System.out.println(today+" :");
      Date d = null;
      for (Holiday hol : holidaySet) {
        d = hol.getDate();
        if ( d.equals( today))
          System.out.println("    " + hol.getName() + "(" + hol.getType() + ")");
//          System.out.println("    " + hol.getName() + "(" + hol.getType() + "-" + hol.getType().hashCode() + ")");
      }
    }
  }