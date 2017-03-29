package etc.jyclapps.testproject.myapplication.Model;

/**
 * Created by Joshua YC Leung on 1/21/2017.
 * TimerDisplay Model Class - Model that contains the data for each timer (start date, end date, alarm times/percentages)
 */

public class TimerDisplay {
    public String start_date;
    public String end_date;
    public String start_datetime;
    public String end_datetime;
    public float percentage;

    public TimerDisplay() { //(String date_started, String date_ended, float percentage_left) {
//        this.start_date = date_started;
//        this.end_date = date_ended;
//        this.percentage = percentage_left;
    }

    //set methods (for assigning data)
    public void setCurrentDate(String date_started) {
        this.start_date = date_started;
    }

    public void setEndDate(String date_ended) {
        this.end_date = date_ended;
    }

    public void setPercentage() {
        this.percentage = 50;
    }

    public void setStartDateTime(String datetime_started) {
        this.start_datetime = datetime_started;
    }

    public void setEndDateTime(String datetime_ended) {
        this.end_datetime = datetime_ended;
    }

    //get methods (for retrieving data)

    public String getStartDateTime() {
        return this.start_datetime;
    }

    public String getEndDateTime() {
        return this.end_datetime;
    }

    public String getStartDate() {
        return this.start_date;
    }

    public String getCurrentDate() {
        return this.start_date;
    }

    public String getEndDate() {
        return this.end_date;
    }

    public float getPercentage() {
        return this.percentage;
    }

    //calculation in model or adapter?
}
