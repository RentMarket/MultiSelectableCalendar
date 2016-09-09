package dokup.xyz.multiselectablecalendar.comparator;

import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by e10dokup on 2016/09/09
 **/
public class CalendarComparator implements Comparator<Calendar> {
    private static final String TAG = CalendarComparator.class.getSimpleName();
    private final CalendarComparator self = this;

    @Override
    public int compare(Calendar c1, Calendar c2) {
        int diff = c1.compareTo(c2);
        return diff >= 0 ? 1 : -1;
    }
}