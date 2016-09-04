package dokup.xyz.multiselectablecalendar.entity;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class SimpleDateTest {

    @Test
    public void doConvertSimpleDateToCollectCalendar() throws Exception {
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        Calendar calendar = SimpleDate.simpleDateToCalendar(simpleDate);
        assertThat(calendar.get(Calendar.YEAR)).isEqualTo(simpleDate.getYear());
        assertThat(calendar.get(Calendar.MONTH)).isEqualTo(simpleDate.getMonth());
        assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(simpleDate.getDay());
    }
}