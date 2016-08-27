package dokup.xyz.multiselectablecalendar.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by e10dokup on 2016/08/27
 **/
public class AvailableSchedule {
    private static final String TAG = AvailableSchedule.class.getSimpleName();
    private final AvailableSchedule self = this;

    private List<Date> mAvailableDateList = new ArrayList<>();
    private List<Date> mUnavailableDateList = new ArrayList<>();

    public List<Date> getAvailableDateList() {
        return mAvailableDateList;
    }

    public void setAvailableDateList(List<Date> availableDateList) {
        mAvailableDateList = availableDateList;
    }

    public List<Date> getUnavailableDateList() {
        return mUnavailableDateList;
    }

    public void setUnavailableDateList(List<Date> unavailableDateList) {
        mUnavailableDateList = unavailableDateList;
    }
}