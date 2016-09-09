package dokup.xyz.multiselectablecalendar.listener;

import java.util.Calendar;
import java.util.List;

public interface OnScheduleChangedListener {
    void onScheduleChanged(List<Calendar> availableSchedule, List<Calendar> unavailableSchedule);
}
