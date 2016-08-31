package dokup.xyz.multiselectablecalendar.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by e10dokup on 2016/08/27
 **/
public class AvailableSchedule {
    private static final String TAG = AvailableSchedule.class.getSimpleName();
    private final AvailableSchedule self = this;

    private List<Calendar> mAvailableCalendarList = new ArrayList<>();
    private List<Calendar> mUnavailableCalendarList = new ArrayList<>();

    public List<Calendar> getAvailableCalendarList() {
        return mAvailableCalendarList;
    }

    public void setAvailableCalendarList(List<Calendar> availableCalendarList) {
        mAvailableCalendarList = availableCalendarList;
    }

    public List<Calendar> getUnavailableCalendarList() {
        return mUnavailableCalendarList;
    }

    public void setUnavailableCalendarList(List<Calendar> unavailableCalendarList) {
        mUnavailableCalendarList = unavailableCalendarList;
    }

    public void addAvailableCalendarList(Calendar calendar) {
        mAvailableCalendarList.add(calendar);
    }

    public void addAvailableCalendarList(SimpleDate simpleDate) {
        addAvailableCalendarList(simpleCalendarToCalendar(simpleDate));
    }

    public void addUnavailableCalendarList(Calendar calendar) {
        mUnavailableCalendarList.add(calendar);
    }

    public void addUnavailableCalendarList(SimpleDate simpleDate) {
        addUnavailableCalendarList(simpleCalendarToCalendar(simpleDate));
    }

    public boolean removeAvailableCalendarList(Calendar calendar) {
        for (Calendar c : mAvailableCalendarList) {
            boolean isYearMatched = c.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
            boolean isMonthMatched = c.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
            boolean isDayMatched = c.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
            if(isYearMatched && isMonthMatched && isDayMatched) return mAvailableCalendarList.remove(c);
        }
        return false;
    }

    public boolean removeAvailableCalendarList(SimpleDate simpleDate) {
        return removeAvailableCalendarList(simpleCalendarToCalendar(simpleDate));
    }

    public boolean removeUnavailableCalendarList(Calendar calendar) {
        for (Calendar c : mUnavailableCalendarList) {
            boolean isYearMatched = c.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
            boolean isMonthMatched = c.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
            boolean isDayMatched = c.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
            if(isYearMatched && isMonthMatched && isDayMatched) return mUnavailableCalendarList.remove(c);
        }
        return false;
    }

    public boolean removeUnavailableCalendarList(SimpleDate simpleDate) {
        return removeUnavailableCalendarList(simpleCalendarToCalendar(simpleDate));
    }

    public boolean isIncludeAvailableCalendarList(Calendar calendar) {
        for (Calendar c : mAvailableCalendarList) {
            boolean isYearMatched = c.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
            boolean isMonthMatched = c.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
            boolean isDayMatched = c.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
            if(isYearMatched && isMonthMatched && isDayMatched) return true;
        }
        return false;
    }

    public boolean isIncludeAvailableCalendarList(SimpleDate simpleDate) {
        return isIncludeAvailableCalendarList(simpleCalendarToCalendar(simpleDate));
    }

    public boolean isIncludeUnavailableCalendarList(Calendar calendar) {
        for (Calendar c : mUnavailableCalendarList) {
            boolean isYearMatched = c.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
            boolean isMonthMatched = c.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
            boolean isDayMatched = c.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
            if(isYearMatched && isMonthMatched && isDayMatched) return true;
        }
        return false;
    }

    public boolean isIncludeUnavailableCalendarList(SimpleDate simpleDate) {
        return isIncludeUnavailableCalendarList(simpleCalendarToCalendar(simpleDate));
    }

    private static Calendar simpleCalendarToCalendar(SimpleDate simpleDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(simpleDate.getYear(), simpleDate.getMonth(), simpleDate.getDay());
        return calendar;
    }
}