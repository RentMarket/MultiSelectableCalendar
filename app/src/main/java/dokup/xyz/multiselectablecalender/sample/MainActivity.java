package dokup.xyz.multiselectablecalender.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dokup.xyz.multiselectablecalendar.ui.MultiSelectableCalenderView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiSelectableCalenderView calenderView = (MultiSelectableCalenderView) findViewById(R.id.calender);
        calenderView.set(2016, 7);
    }
}


