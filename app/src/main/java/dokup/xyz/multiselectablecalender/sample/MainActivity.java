package dokup.xyz.multiselectablecalender.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

import dokup.xyz.multiselectablecalendar.ui.MultiSelectableCalenderView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiSelectableCalenderView calenderView = (MultiSelectableCalenderView) findViewById(R.id.calender);
        Calendar calendar = Calendar.getInstance();
        calenderView.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }
}


