package dokup.xyz.multiselectablecalendar.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by e10dokup on 2016/08/26
 **/
public class MultiSelectableCalenderView extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = MultiSelectableCalenderView.class.getSimpleName();
    @SuppressWarnings("unused")
    private final MultiSelectableCalenderView self = this;

    private static final int COLUMN_SIZE = 7;
    private static final int ROW_SIZE = 6;

    private static final String FORMAT_YEAR_MONTH = "yyyy/MM";
    private static final String FORMAT_WEEKDAYS = "E";

    // DayOfWeek which beginning day of month
    private int mBeginningDayOfWeek = Calendar.SUNDAY;

    // Indicate Month
    private TextView mMonthText;

    // Week Layout
    private LinearLayout mWeekLayout;

    private ArrayList<LinearLayout> mWeekLayoutList = new ArrayList<>();

    public MultiSelectableCalenderView(Context context) {
        super(context);
        createViews(context);
    }

    public MultiSelectableCalenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createViews(context);
    }

    public MultiSelectableCalenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createViews(context);
    }

    public void set(int year, int month) {
        setMonth(year, month - 1);
        setWeekDays();
        setDays(year, month - 1);
    }

    private void createViews(Context context) {
        setOrientation(VERTICAL);
        createMonth(context);
        createWeekViews(context);
        createDayViews(context);
    }

    /**
     * Create view for indicate month
     * @param context context
     */
    private void createMonth(Context context) {
        float scaleDensity = context.getResources().getDisplayMetrics().density;

        mMonthText = new TextView(context);
        mMonthText.setGravity(Gravity.CENTER_HORIZONTAL);
        mMonthText.setTextSize((int) scaleDensity * 5);
        mMonthText.setTypeface(null, Typeface.BOLD);
        mMonthText.setPadding(0, 0, 0, (int) scaleDensity * 10);

        addView(mMonthText, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    /**
     * Create views for indicate weekdays
     * @param context context
     */
    private void createWeekViews(Context context) {
        float scaleDensity = context.getResources().getDisplayMetrics().density;
        mWeekLayout = new LinearLayout(context);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, mBeginningDayOfWeek);

        for(int i=0; i<COLUMN_SIZE; i++) {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(0, 0, 0, 0);
            LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;

            mWeekLayout.addView(textView, layoutParams);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        addView(mWeekLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }


    /**
     * Create views for indicate days
     * @param context context
     */
    private void createDayViews(Context context) {
        float scaleDensity = context.getResources().getDisplayMetrics().density;

        for(int i=0; i<ROW_SIZE; i++) {
            LinearLayout weekRow = new LinearLayout(context);
            mWeekLayoutList.add(weekRow);

            // Create 1 week view
            for(int j=0; j<COLUMN_SIZE; j++) {
                TextView dayText = new TextView(context);
                dayText.setGravity(Gravity.CENTER);
                int padding = (int)(scaleDensity * 2);
                dayText.setPadding(padding, padding, padding, padding);
                LayoutParams layoutParams = new LayoutParams(0, (int)(scaleDensity * 48));
                layoutParams.weight = 1;
                weekRow.addView(dayText, layoutParams);
            }

            addView(weekRow, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }

    /**
     * Set assigned month to top Month indicator
     * @param year assigned year
     * @param month assigned month
     */
    @SuppressLint("SimpleDateFormat")
    private void setMonth(int year, int month) {
        Calendar assignedCalender = getAssignedCalendar(year, month);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YEAR_MONTH);

        mMonthText.setText(sdf.format(assignedCalender.getTime()));
    }

    /**
     * Set weekdays to weekdays indicator
     */
    @SuppressLint("SimpleDateFormat")
    private void setWeekDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, mBeginningDayOfWeek);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_WEEKDAYS);

        for(int i=0; i<COLUMN_SIZE; i++) {
            TextView textView = (TextView) mWeekLayout.getChildAt(i);
            textView.setText(sdf.format(calendar.getTime()));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void setDays(int year, int month) {
        Calendar assignedCalendar = getAssignedCalendar(year, month);
        int skipCount = getSkipCount(assignedCalendar);
        int lastDay = assignedCalendar.getActualMaximum(Calendar.DATE);
        int dayCount = 1;

        for(int i=0; i<ROW_SIZE; i++) {
            LinearLayout weekLayout = mWeekLayoutList.get(i);
            for(int j=0; j<COLUMN_SIZE; j++) {
                TextView dayText = (TextView) weekLayout.getChildAt(j);

                if(i==0 && skipCount > 0) {
                    dayText.setText(" ");
                    skipCount--;
                    continue;
                }

                if(lastDay < dayCount) {
                    dayText.setText(" ");
                    continue;
                }

                dayText.setText(String.valueOf(dayCount));
                dayCount++;
            }
        }
    }

    private Calendar getAssignedCalendar(int year, int month) {
        Calendar assignedCalendar = Calendar.getInstance();
        assignedCalendar.clear();
        assignedCalendar.set(Calendar.YEAR, year);
        assignedCalendar.set(Calendar.MONTH, month);
        assignedCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return assignedCalendar;
    }

    private int getSkipCount(Calendar assignedCalendar) {
        int firstDayOfWeekOfMonth = assignedCalendar.get(Calendar.DAY_OF_WEEK);
        return mBeginningDayOfWeek > firstDayOfWeekOfMonth ? firstDayOfWeekOfMonth - mBeginningDayOfWeek + COLUMN_SIZE : firstDayOfWeekOfMonth - mBeginningDayOfWeek;
    }
}