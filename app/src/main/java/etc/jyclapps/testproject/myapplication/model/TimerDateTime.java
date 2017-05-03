package etc.jyclapps.testproject.myapplication.model;

import etc.jyclapps.testproject.myapplication.tools.StringIntFormatter;

/**
 * Created by Joshua YC Leung on 1/21/2017.
 * TimerDateTime Model Class - Model that contains the data for each timer (start date, end date, alarm times/percentages)
 */

public class TimerDateTime {
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;

    public TimerDateTime(int dayOfMonth, int month, int year, int hour, int minute) {
        this.day = dayOfMonth;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    //set methods (for assigning data)
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }


//    public void setDate(int dayOfMonth, int month, int year) {
//        end_date_day = dayOfMonth;
//        end_date_month = month;
//        end_date_year = year;
//        this.percentage = 50;
//    }


    //get methods (for retrieving data)

    public String getFormattedTime() {
        String time_formatted = StringIntFormatter.formatIntToString(hour, 2) + ":" + StringIntFormatter.formatIntToString(minute, 2);
        return time_formatted;
    }

    public String getFormattedHour() {
        String hour_formatted = StringIntFormatter.formatIntToString(hour, 2);
        return hour_formatted;
    }

    public String getFormattedMinute() {
        String min_formatted = StringIntFormatter.formatIntToString(minute, 2);
        return min_formatted;
    }

    public String getFormattedDateDayMonthYear() {
        String date_formatted = StringIntFormatter.formatIntToString(day, 2) + " " + StringIntFormatter.formatIntToString(month, 2) + " " + StringIntFormatter.formatIntToString(year, 4);
        return date_formatted;
    }

    public String getFormattedDateDayMonthYearNoSpace() {
        String date_formatted = StringIntFormatter.formatIntToString(day, 2) + StringIntFormatter.formatIntToString(month, 2) + StringIntFormatter.formatIntToString(year, 4);
        return date_formatted;
    }

    public String getFormattedDay() {
        String hour_formatted = StringIntFormatter.formatIntToString(day, 2);
        return hour_formatted;
    }

    public String getFormattedMonth() {
        String hour_formatted = StringIntFormatter.formatIntToString(month, 2);
        return hour_formatted;
    }

    public String getFormattedYear() {
        String hour_formatted = StringIntFormatter.formatIntToString(year, 4);
        return hour_formatted;
    }

    //calculation in model or adapter?
}
