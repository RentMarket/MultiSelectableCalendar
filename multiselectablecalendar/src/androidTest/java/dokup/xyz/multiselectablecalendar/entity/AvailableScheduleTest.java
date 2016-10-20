package dokup.xyz.multiselectablecalendar.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AvailableScheduleTest {

    private AvailableSchedule mAvailableSchedule;

    @Test
    public void doAddAvailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        assertThat(mAvailableSchedule.getAvailableCalendarList().size()).isEqualTo(1);
    }

    @Test
    public void doNotAddAvailableCalendarListWhenExistAvailable() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate2);
        assertThat(mAvailableSchedule.getAvailableCalendarList().size()).isEqualTo(1);
    }

    @Test
    public void doNotAddAvailableCalendarListWhenExistUnavailable() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate2);
        assertThat(mAvailableSchedule.getAvailableCalendarList().size()).isEqualTo(0);
    }

    @Test
    public void doAddUnavailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        assertThat(mAvailableSchedule.getUnavailableCalendarList().size()).isEqualTo(1);
    }

    @Test
    public void doNotAddUnavailableCalendarListWhenExistAvailable() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate2);
        assertThat(mAvailableSchedule.getUnavailableCalendarList().size()).isEqualTo(0);
    }

    @Test
    public void doNotAddUnavailableCalendarListWhenExistUnavailable() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate2);
        assertThat(mAvailableSchedule.getUnavailableCalendarList().size()).isEqualTo(1);
    }

    @Test
    public void doRemoveAvailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        boolean removed = mAvailableSchedule.removeAvailableCalendarList(simpleDate);
        assertThat(removed).isEqualTo(true);
    }

    @Test
    public void doNotRemoveAvailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 2);
        boolean removed = mAvailableSchedule.removeAvailableCalendarList(simpleDate2);
        assertThat(removed).isEqualTo(false);
    }

    @Test
    public void doRemoveUnavailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        boolean removed = mAvailableSchedule.removeUnavailableCalendarList(simpleDate);
        assertThat(removed).isEqualTo(true);
    }

    @Test
    public void doNotRemoveUnavailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 2);
        boolean removed = mAvailableSchedule.removeUnavailableCalendarList(simpleDate2);
        assertThat(removed).isEqualTo(false);
    }

    @Test
    public void returnTrueWhenIncludeAvailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        boolean included = mAvailableSchedule.isIncludeAvailableCalendarList(simpleDate);
        assertThat(included).isEqualTo(true);
    }

    @Test
    public void returnFalseWhenIncludeAvailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 2);
        boolean included = mAvailableSchedule.isIncludeAvailableCalendarList(simpleDate2);
        assertThat(included).isEqualTo(false);
    }

    @Test
    public void returnTrueWhenIncludeUnavailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        boolean included = mAvailableSchedule.isIncludeUnavailableCalendarList(simpleDate);
        assertThat(included).isEqualTo(true);
    }

    @Test
    public void returnFalseWhenIncludeUnavailableCalendarList() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 2);
        boolean included = mAvailableSchedule.isIncludeUnavailableCalendarList(simpleDate2);
        assertThat(included).isEqualTo(false);
    }

    @Test
    public void doClearSchedule() {
        mAvailableSchedule = new AvailableSchedule();
        SimpleDate simpleDate = new SimpleDate(2016, 0, 1);
        mAvailableSchedule.addAvailableCalendarList(simpleDate);
        SimpleDate simpleDate2 = new SimpleDate(2016, 0, 2);
        mAvailableSchedule.addUnavailableCalendarList(simpleDate2);
        mAvailableSchedule.clearSchedule();
        assertThat(mAvailableSchedule.getAvailableCalendarList().size()).isEqualTo(0);
        assertThat(mAvailableSchedule.getUnavailableCalendarList().size()).isEqualTo(0);
    }

    @Test
    public void doSortAvailableCalendarList() {
        AvailableSchedule as = new AvailableSchedule();
        SimpleDate d0 = new SimpleDate(2016, 0, 1);
        SimpleDate d1 = new SimpleDate(2015, 11, 1);
        SimpleDate d2 = new SimpleDate(2016, 1, 23);
        SimpleDate d3 = new SimpleDate(2016, 2, 12);
        SimpleDate d4 = new SimpleDate(2016, 1, 5);
        as.addAvailableCalendarList(d4);
        as.addAvailableCalendarList(d0);
        as.addAvailableCalendarList(d1);
        as.addAvailableCalendarList(d2);
        as.addAvailableCalendarList(d3);
        List<Calendar> expected = new ArrayList<>();
        expected.add(SimpleDate.simpleDateToCalendar(d1));
        expected.add(SimpleDate.simpleDateToCalendar(d0));
        expected.add(SimpleDate.simpleDateToCalendar(d4));
        expected.add(SimpleDate.simpleDateToCalendar(d2));
        expected.add(SimpleDate.simpleDateToCalendar(d3));
        List<Calendar> actual = as.getAvailableCalendarList();
        for(int i=0; i<actual.size(); i++) {
            assertThat(actual.get(i).compareTo(expected.get(i))).isEqualTo(0);
        }
    }

    @Test
    public void doSortUnavailableCalendarList() {
        AvailableSchedule as = new AvailableSchedule();
        SimpleDate d0 = new SimpleDate(2016, 0, 1);
        SimpleDate d1 = new SimpleDate(2015, 11, 1);
        SimpleDate d2 = new SimpleDate(2016, 1, 23);
        SimpleDate d3 = new SimpleDate(2016, 2, 12);
        SimpleDate d4 = new SimpleDate(2016, 1, 5);
        as.addUnavailableCalendarList(d0);
        as.addUnavailableCalendarList(d1);
        as.addUnavailableCalendarList(d2);
        as.addUnavailableCalendarList(d3);
        as.addUnavailableCalendarList(d4);
        List<Calendar> expected = new ArrayList<>();
        expected.add(SimpleDate.simpleDateToCalendar(d1));
        expected.add(SimpleDate.simpleDateToCalendar(d0));
        expected.add(SimpleDate.simpleDateToCalendar(d4));
        expected.add(SimpleDate.simpleDateToCalendar(d2));
        expected.add(SimpleDate.simpleDateToCalendar(d3));
        List<Calendar> actual = as.getUnavailableCalendarList();
        for(int i=0; i<actual.size(); i++) {
            assertThat(actual.get(i).compareTo(expected.get(i))).isEqualTo(0);
        }
    }

}