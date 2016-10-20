package dokup.xyz.multiselectablecalender.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

import dokup.xyz.multiselectablecalendar.entity.AvailableSchedule;
import dokup.xyz.multiselectablecalendar.ui.MultiSelectableCalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiSelectableCalendarView calenderView = (MultiSelectableCalendarView) findViewById(R.id.calender);
        Calendar calendar = Calendar.getInstance();
        AvailableSchedule schedule = new AvailableSchedule();
        schedule.addUnavailableCalendarList(calendar);
        calenderView.setAvailableSchedule(schedule);
        calenderView.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }
}


