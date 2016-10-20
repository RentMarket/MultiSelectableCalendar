package dokup.xyz.multiselectablecalendar.entity;

import org.junit.Test;

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

}