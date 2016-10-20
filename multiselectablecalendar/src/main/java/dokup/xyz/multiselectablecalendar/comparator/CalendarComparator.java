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
        if(c1.before(c2)) return -1;
        if(c1.after(c2)) return 1;
        return 0;
    }
}