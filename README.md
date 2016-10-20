# MultiSelectableCalendar

[ ![Download](https://api.bintray.com/packages/e10dokup/maven/multiselectablecalendar/images/download.svg) ](https://bintray.com/e10dokup/maven/multiselectablecalendar/_latestVersion) [![wercker status](https://app.wercker.com/status/59867b79f0f04a9b9259a88c364aca8c/s/master "wercker status")](https://app.wercker.com/project/byKey/59867b79f0f04a9b9259a88c364aca8c)

## About

* Simple calendar having available/unavailable state each day for Android

## Download
 
```
dependencies {
    compile 'xyz.dokup.multiselectablecalendar:multiselectablecalendar:x.y.z'
}
```

## XML Attributes

``` XML

<dokup.xyz.multiselectablecalendar.ui.MultiSelectableCalenderView
        android:id="@+id/calender"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:monthBackgroundColor="#81D4FA" <!-- color of background parts for month -->
        app:monthTextColor="#546E7A" <!-- color of text for month -->
        app:weekBackgroundColor="#03A9F4" <!-- color of background parts for week -->
        app:weekTextColor="#FFFFFF" <!-- color of text for month -->
        app:dayBackgroundColor="#FFFFFF" <!-- color of background for date -->
        app:dayTextColor="#616161" <!-- color of text for date -->
        app:availableDayBackgroundColor="#FF9800" <!-- color of background for date which is available -->
        app:availableDayTextColor="#FFFFFF" <!-- color of text for date which is available -->
        app:unavailableDayBackgroundColor="#757575" <!-- color of background for date which is unavailable -->
        app:unavailableDayTextColor="#FFFFFF" <!-- color of text for date which is unavailable -->
        app:chevronColor="#1565C0" <!-- color of chevron for month change -->
        />

```

## Examples

### show calendar

``` java

MultiSelectableCalendarView calendarView = (MultiSelectableCalenderView) findViewById(R.id.calender);
Calendar calendar = Calendar.getInstance();
calendarView.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

```

### set available/unavailable schedules

``` java

AvailableSchedule schedule = new AvailableSchedule(); // Instantiate schedule holder
schedule.addAvailableCalendarList(calendar1); // set java.util.Calendar instance as available schedule
schedule.addUnavailableCalendarList(calendar2); // set java.util.Calendar instance as unavailable schedule

MultiSelectableCalendarView calendarView = (MultiSelectableCalenderView) findViewById(R.id.calender);
Calendar calendar = Calendar.getInstance();
calendarView.setAvailableSchedule(schedule); // set AvailableSchedule
calendarView.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

```

### get available/unavailable schedules

``` java

AvailableSchedule schedule = calendarView.getAvailableSchedule // get AvailableSchedule instance
List<Calendar> available = schedule.getAvailableCalendarList(); // get available date as java.util.Calendar's list
List<Calendar> unavailable = schedule.getUnavailableCalendarList(); // get unavailable date as java.util.Calendar's list

```

### remove and clear available/unavailable schedules

``` java

availableSchedule.removeAvailableCalendarList(calendar1) // remove java.util.Calendar instance from available date list
availableSchedule.removeUnavailableCalendarList(calendar1) // remove java.util.Calendar instance from unavailable date list
availableSchedule.clear() // clear schedules

```

### set calendar mode

MultiSelectableCalendarView has some mode for set and show schedule. You can set mode by `MultiSelectableCalendarView#setMode(ScheduleMode mode)`.

* `ScheduleMode.SINGLE`
    * Allow select single date to chenge state of available/unavailable/nothing
    * State change available -> unavailable -> nothing -> available -> ...
* `ScheduleMode.RANGE`
    * Allow select A to B dates to cange state to available
    * You can set unavailable days on before and after ranged date by using `MultiSelectableCalendarView#setInterval(int interval)`
* `ScheduleMode.DISPLAY`
    * Allow no select
    * For use only to indicate schedule

### change year and month formats

Format is able to been change by `MultiSelectableCalendarView#setYearMonthFormat(String format)`. 

* Example `"yyyy/MM"`, `"MM/yyyy"`

### listener on changed schedule

If you use `MultiSelectableCalendarView#setOnScheduleChangedListener(OnScheduleChangedListener listener)`, you can get callback when schedules are changed by touch calendar.

## License

```
Copyright 2016 e10dokup

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
