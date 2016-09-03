package dokup.xyz.multiselectablecalendar.entity;

import java.util.Calendar;

/**
 * Created by e10dokup on 2016/08/27
 **/
public class SimpleDate {
    private static final String TAG = SimpleDate.class.getSimpleName();
    private final SimpleDate self = this;

    private int mYear;
    private int mMonth;
    private int mDay;

    public SimpleDate(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public static Calendar simpleDateToCalendar(SimpleDate simpleDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(simpleDate.getYear(), simpleDate.getMonth(), simpleDate.getDay());
        return calendar;
    }
}