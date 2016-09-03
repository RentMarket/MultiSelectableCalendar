package dokup.xyz.multiselectablecalendar.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dokup.xyz.multiselectablecalendar.R;
import dokup.xyz.multiselectablecalendar.entity.AvailableSchedule;
import dokup.xyz.multiselectablecalendar.entity.SimpleDate;
import dokup.xyz.multiselectablecalendar.util.ScheduleMode;

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
    private LinearLayout mMonthLayout;
    private TextView mMonthText;
    private ImageView mPreviousImage;
    private ImageView mNextImage;

    // Week Layout
    private LinearLayout mWeekLayout;

    private ArrayList<LinearLayout> mWeekLayoutList = new ArrayList<>();

    private int mYear;
    private int mMonth;

    private AvailableSchedule mAvailableSchedule = new AvailableSchedule();

    private Context mContext;

    //attributes
    private int mMonthBackgroundColor;
    private int mMonthTextColor;
    private int mWeekBackgroundColor;
    private int mWeekTextColor;
    private int mDayBackgroundColor;
    private int mDayTextColor;
    private int mAvailableDayBackgroundColor;
    private int mAvailableDayTextColor;
    private int mUnavailableDayTextColor;
    private int mUnavailableDayBackgroundColor;
    private int mChevronColor;

    private ScheduleMode mMode = ScheduleMode.RANGE;
    private int mInterval = 3;

    private SimpleDate mFirstSelectedDate;
    private SimpleDate mSecondSelectedDate;


    public MultiSelectableCalenderView(Context context) {
        super(context);
        mContext = context;
        mMonthBackgroundColor = ContextCompat.getColor(mContext, R.color.default_background_month);
        mMonthTextColor = ContextCompat.getColor(mContext, R.color.text_color);
        mWeekBackgroundColor = ContextCompat.getColor(mContext, R.color.white);
        mWeekTextColor = ContextCompat.getColor(mContext, R.color.text_color);
        mDayBackgroundColor = ContextCompat.getColor(mContext, R.color.white);
        mDayTextColor = ContextCompat.getColor(mContext, R.color.text_color);
        mAvailableDayBackgroundColor = ContextCompat.getColor(mContext, R.color.available_day_background);
        mAvailableDayTextColor = ContextCompat.getColor(mContext, R.color.white);
        mUnavailableDayBackgroundColor = ContextCompat.getColor(mContext, R.color.unavailable_day_background);
        mUnavailableDayTextColor = ContextCompat.getColor(mContext, R.color.text_color);
        mChevronColor = ContextCompat.getColor(mContext, R.color.text_color);
        createViews();
    }

    public MultiSelectableCalenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setAttributes(attrs);
        createViews();
    }

    public MultiSelectableCalenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setAttributes(attrs);
        createViews();
    }

    public void setMode(ScheduleMode mode) {
        mMode = mode;
    }

    public int getInterval() {
        return mInterval;
    }

    public void setInterval(int interval) {
        mInterval = interval;
    }

    public void set(int year, int month) {
        mYear = year;
        mMonth = month;
        setMonth(year, mMonth);
        setWeekDays();
        setDays(year, mMonth);
    }

    private void setAttributes(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MultiSelectableCalenderView);
        mMonthBackgroundColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_monthBackgroundColor, ContextCompat.getColor(mContext, R.color.default_background_month));
        mMonthTextColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_monthTextColor, ContextCompat.getColor(mContext, R.color.text_color));
        mWeekBackgroundColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_weekBackgroundColor, ContextCompat.getColor(mContext, R.color.white));
        mWeekTextColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_weekTextColor, ContextCompat.getColor(mContext, R.color.text_color));
        mDayBackgroundColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_dayBackgroundColor, ContextCompat.getColor(mContext, R.color.white));
        mDayTextColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_dayTextColor, ContextCompat.getColor(mContext, R.color.text_color));
        mAvailableDayBackgroundColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_availableDayBackgroundColor, ContextCompat.getColor(mContext, R.color.available_day_background));
        mAvailableDayTextColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_availableDayTextColor, ContextCompat.getColor(mContext, R.color.white));
        mUnavailableDayBackgroundColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_unavailableDayBackgroundColor, ContextCompat.getColor(mContext, R.color.unavailable_day_background));
        mUnavailableDayTextColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_unavailableDayTextColor, ContextCompat.getColor(mContext, R.color.text_color));
        mChevronColor = typedArray.getColor(R.styleable.MultiSelectableCalenderView_chevronColor, ContextCompat.getColor(mContext, R.color.text_color));
    }

    private void createViews() {
        setOrientation(VERTICAL);
        createMonth();
        createWeekViews();
        createDayViews();
    }

    /**
     * Create view for indicate month
     * @param
     */
    private void createMonth() {
        float scaleDensity = mContext.getResources().getDisplayMetrics().density;

        mMonthLayout = new LinearLayout(mContext);
        mMonthLayout.setOrientation(HORIZONTAL);
        mMonthLayout.setGravity(Gravity.CENTER);
        mMonthLayout.setPadding(0, (int) scaleDensity * 20, 0, (int) scaleDensity * 20);

        mPreviousImage = new ImageView(mContext);
        Drawable leftChevron = ContextCompat.getDrawable(mContext, R.drawable.lib_ic_chevron_left_grey_500_24dp);
        DrawableCompat.setTint(leftChevron, mChevronColor);
        DrawableCompat.setTintMode(leftChevron, PorterDuff.Mode.SRC_IN);
        mPreviousImage.setImageDrawable(leftChevron);
        mPreviousImage.setPadding((int) scaleDensity * 10, 0, (int) scaleDensity * 10, 0);
        mPreviousImage.setOnClickListener(mOnPreviousClickListener);
        mMonthLayout.addView(mPreviousImage, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        mMonthText = new TextView(mContext);
        mMonthText.setGravity(Gravity.CENTER_HORIZONTAL);
        mMonthText.setTextSize((int) scaleDensity * 5);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        mMonthLayout.addView(mMonthText, layoutParams);

        mNextImage = new ImageView(mContext);
        Drawable rightChevron = ContextCompat.getDrawable(mContext, R.drawable.lib_ic_chevron_right_grey_500_24dp);
        DrawableCompat.setTint(rightChevron, mChevronColor);
        DrawableCompat.setTintMode(rightChevron, PorterDuff.Mode.SRC_IN);
        mNextImage.setImageDrawable(rightChevron);
        mNextImage.setPadding((int) scaleDensity * 10, 0, (int) scaleDensity * 10, 0);
        mNextImage.setOnClickListener(mOnNextClickListener);
        mMonthLayout.addView(mNextImage, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        addView(mMonthLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    /**
     * Create views for indicate weekdays
     * @param
     */
    private void createWeekViews() {
        float scaleDensity = mContext.getResources().getDisplayMetrics().density;
        mWeekLayout = new LinearLayout(mContext);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, mBeginningDayOfWeek);

        for(int i=0; i<COLUMN_SIZE; i++) {
            TextView weekText = new TextView(mContext);
            weekText.setGravity(Gravity.CENTER);
            weekText.setPadding(0, (int) scaleDensity * 5, 0, (int) scaleDensity * 5);
            LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            weekText.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));

            mWeekLayout.addView(weekText, layoutParams);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        addView(mWeekLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }


    /**
     * Create views for indicate days
     * @param
     */
    private void createDayViews() {
        float scaleDensity = mContext.getResources().getDisplayMetrics().density;

        for(int i=0; i<ROW_SIZE; i++) {
            LinearLayout weekRow = new LinearLayout(mContext);
            mWeekLayoutList.add(weekRow);

            // Create 1 week view
            for(int j=0; j<COLUMN_SIZE; j++) {
                TextView dayText = new TextView(mContext);
                dayText.setGravity(Gravity.CENTER);
                int padding = (int)(scaleDensity * 2);
                dayText.setPadding(padding, padding, padding, padding);
                LayoutParams layoutParams = new LayoutParams(0, (int)(scaleDensity * 48));
                layoutParams.weight = 1;
                dayText.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
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
        mMonthLayout.setBackgroundColor(mMonthBackgroundColor);
        mMonthText.setTextColor(mMonthTextColor);

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
            textView.setBackgroundColor(mWeekBackgroundColor);
            textView.setTextColor(mWeekTextColor);
            textView.setText(sdf.format(calendar.getTime()));


            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    /**
     * Set assigned day of month for indicate days
     * @param year assigned year
     * @param month assigned month
     */
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
                    dayText.setOnClickListener(null);
                    skipCount--;
                    dayText.setBackgroundColor(mDayBackgroundColor);
                    continue;
                }

                if(lastDay < dayCount) {
                    dayText.setText(" ");
                    dayText.setOnClickListener(null);
                    dayText.setBackgroundColor(mDayBackgroundColor);
                    continue;
                }

                dayText.setText(String.valueOf(dayCount));
                dayText.setOnClickListener(mOnDateClickListener);
                SimpleDate simpleDate = new SimpleDate(mYear, mMonth, dayCount);
                if(mAvailableSchedule.isIncludeAvailableCalendarList(simpleDate)) {
                    dayText.setBackgroundColor(mAvailableDayBackgroundColor);
                    dayText.setTextColor(mAvailableDayTextColor);
                } else if(mAvailableSchedule.isIncludeUnavailableCalendarList(simpleDate)) {
                    dayText.setBackgroundColor(mUnavailableDayBackgroundColor);
                    dayText.setTextColor(mUnavailableDayTextColor);
                } else {
                    dayText.setBackgroundColor(mDayBackgroundColor);
                    dayText.setTextColor(mDayTextColor);
                }
                dayCount++;
            }
        }
    }

    /**
     * Get calendar instance which assigned month
     * @param year assigned year
     * @param month assigned month
     * @return
     */
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

    private OnClickListener mOnDateClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mMode == ScheduleMode.SINGLE) {
                onClickAtSingleMode(view);
            } else if(mMode == ScheduleMode.RANGE) {
                onClickAsRangeMode(view);
            }
        }
    };

    private void onClickAtSingleMode(View view) {
        TextView dayText = (TextView) view;
        SimpleDate simpleDate = new SimpleDate(mYear, mMonth, Integer.parseInt(dayText.getText().toString()));
        if(mAvailableSchedule.isIncludeAvailableCalendarList(simpleDate)) {
            // When selected day is already available
            mAvailableSchedule.removeAvailableCalendarList(simpleDate);
            mAvailableSchedule.addUnavailableCalendarList(simpleDate);
            dayText.setBackgroundColor(mUnavailableDayBackgroundColor);
            dayText.setTextColor(mUnavailableDayTextColor);
        } else if(mAvailableSchedule.isIncludeUnavailableCalendarList(simpleDate)) {
            // When selected day is already unavailable
            mAvailableSchedule.removeUnavailableCalendarList(simpleDate);
            dayText.setBackgroundColor(mDayBackgroundColor);
            dayText.setTextColor(mDayTextColor);
        } else {
            // When selected day does not have status
            mAvailableSchedule.addAvailableCalendarList(simpleDate);
            dayText.setBackgroundColor(mAvailableDayBackgroundColor);
            dayText.setTextColor(mAvailableDayTextColor);
        }
    }

    private void onClickAsRangeMode(View view) {
        TextView dayText = (TextView) view;
        SimpleDate simpleDate = new SimpleDate(mYear, mMonth, Integer.parseInt(dayText.getText().toString()));
        if(mFirstSelectedDate == null) {
            mAvailableSchedule.clearSchedule();
            set(mYear, mMonth);
            mFirstSelectedDate = simpleDate;
            dayText.setTextColor(mAvailableDayBackgroundColor);
        } else {
            mSecondSelectedDate = simpleDate;
            selectDaysByyRange();
            set(mYear, mMonth);
        }
    }

    private void selectDaysByyRange() {
        Calendar firstCalendar = SimpleDate.simpleDateToCalendar(mFirstSelectedDate);
        Calendar secondCalendar = SimpleDate.simpleDateToCalendar(mSecondSelectedDate);
        mFirstSelectedDate = null;
        mSecondSelectedDate = null;
        int diff = firstCalendar.compareTo(secondCalendar);
        if(diff == 0) {
            return;
        } else if(diff > 0) {
            Calendar tmp = firstCalendar;
            firstCalendar = secondCalendar;
            secondCalendar = tmp;
        }
        int days = (int)((secondCalendar.getTimeInMillis() - firstCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
        Calendar calendar = (Calendar) firstCalendar.clone();
        for(int i=0; i<days; i++) {
            mAvailableSchedule.addAvailableCalendarList((Calendar) calendar.clone());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        for(int i=0; i<mInterval; i++) {
            firstCalendar.add(Calendar.DAY_OF_MONTH, -1);
            secondCalendar.add(Calendar.DAY_OF_MONTH, 1);
            mAvailableSchedule.addUnavailableCalendarList((Calendar) firstCalendar.clone());
            mAvailableSchedule.addUnavailableCalendarList((Calendar) secondCalendar.clone());
        }
    }

    private OnClickListener mOnPreviousClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mMonth == 0) {
                set(mYear - 1, 11);
            } else {
                set(mYear, mMonth - 1);
            }
        }
    };

    private OnClickListener mOnNextClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mMonth == 11) {
                set(mYear + 1, 0);
            } else {
                set(mYear, mMonth + 1);
            }
        }
    };

}