package etc.jyclapps.testproject.myapplication.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import etc.jyclapps.testproject.myapplication.activity.MainActivity;
import etc.jyclapps.testproject.myapplication.helper.DatesDatabaseHelper;
import etc.jyclapps.testproject.myapplication.model.TimerDateTime;
import etc.jyclapps.testproject.myapplication.model.TimerDisplay;
import etc.jyclapps.testproject.myapplication.tools.StringIntFormatter;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Joshua YC Leung on 1/14/2017.
 * Selection Fragment is the parent fragment for different options to select the date.
 * Displays the selected dates for the timer.
 */

public class SelectionFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DialogInterface.OnClickListener,
        DatePickerDialog.OnDateSetListener, DialogFragmentNumberPicker.OnDateTimeSelectedListener {

    DatesDatabaseHelper databaseHelper;

    TimerDisplaySelected timerDisplayStart;
    TimerDisplaySelected timerDisplayEnd;

    private static Button addTimerButton;

    private NumberPicker monthPicker;
    private NumberPicker dayPicker;
    private NumberPicker yearPicker;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;

    private NumberPicker monthPickerEnd;
    private NumberPicker dayPickerEnd;
    private NumberPicker yearPickerEnd;
    private NumberPicker hourPickerEnd;
    private NumberPicker minutePickerEnd;
    //private NumberPicker hourPicker;


    private String monthSelected;
    private String daySelected;
    private String yearSelected;

    private String hourSelected;
    private String minuteSelected;
    private String secondSelected;

    public TimerDateTime start_date;
    public TimerDateTime end_date;

    private TextView selected_start_date;
    private TextView selected_end_date;
    private String time_selected;
    private String date_selected;
    private String datetime_selected;
    private String time_selected_end;
    private String date_selected_end;
    private String datetime_selected_end;

    private boolean edit_start_date = false;
    private boolean edit_end_date = false;

    /**
     * SelectionFragment constructor called by SelectionTypeAdapter (to display various input types)
     * @return SelectionFragment
     */
    public static SelectionFragment newInstance() {
        SelectionFragment fragment = new SelectionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.selection_fragment_start, container, false); //example http://stackoverflow.com/questions/6495898/

        initializeView(view);

//        timerDisplayStart;
//        timerDisplayEnd


        // Add Timer Button submits the selected timer dates into the DB
//        Button addTimerButton = (Button) view.findViewById(R.id.add_timer_button); //        View.OnClickListener
//        addTimerButton.setOnClickListener(buttonClickListener);



        return view;

    }

    /**
     * Method called to initialize all the views and set the layout display
     * @param view
     */
    private void initializeView(View view) {

        //Set Buttons and corresponding Listeners
        Button setTimerButton = (Button) view.findViewById(R.id.add_timer_button);
        setTimerButton.setOnClickListener(this);

        Button addStartNumberPickerButton = (Button) view.findViewById(R.id.add_start_date_number_picker);
        addStartNumberPickerButton.setOnClickListener(this);

        Button addStartCalendarButton = (Button) view.findViewById(R.id.add_start_date_calendar);
        addStartCalendarButton.setOnClickListener(this);

        Button addStartTimeButton = (Button) view.findViewById(R.id.add_start_time);
        addStartTimeButton.setOnClickListener(this);

        Button addEndNumberPickerButton = (Button) view.findViewById(R.id.add_end_date_number_picker);
        addEndNumberPickerButton.setOnClickListener(this);

        Button addEndCalendarButton = (Button) view.findViewById(R.id.add_end_date_calendar);
        addEndCalendarButton.setOnClickListener(this);

        Button addEndTimeButton = (Button) view.findViewById(R.id.add_end_time);
        addEndTimeButton.setOnClickListener(this);

        //Set TextViews and Displays for Dates
        selected_start_date = (TextView) view.findViewById(R.id.selected_date_start);
        Calendar cal = Calendar.getInstance();
        String month = StringIntFormatter.formatIntToString(cal.get(Calendar.MONTH) + 1,2); //month returned from calendar is 0-11, so need to add 1
        time_selected = String.format(Locale.ENGLISH, "%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.format(Locale.ENGLISH, "%02d", cal.get(Calendar.MINUTE));
        date_selected = String.format(Locale.ENGLISH, "%02d", cal.get(Calendar.DAY_OF_MONTH)) + " " + month + " " + String.format(Locale.ENGLISH, "%04d", cal.get(Calendar.YEAR));
        start_date = new TimerDateTime(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR),cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)); //day month year hour minute
        datetime_selected = date_selected + " " + time_selected;
        selected_start_date.setText(datetime_selected);

        selected_end_date = (TextView) view.findViewById(R.id.selected_date_end);
        Calendar calNextDay = Calendar.getInstance();
        calNextDay.add(Calendar.DATE, 1);
        time_selected = String.format(Locale.ENGLISH, "%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.format(Locale.ENGLISH, "%02d", cal.get(Calendar.MINUTE));
        date_selected = String.format(Locale.ENGLISH, "%02d", calNextDay.get(Calendar.DAY_OF_MONTH)) + " " + month + " " + String.format(Locale.ENGLISH, "%04d", cal.get(Calendar.YEAR));
        end_date = new TimerDateTime(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MONTH) + 1,cal.get(Calendar.YEAR),calNextDay.get(Calendar.HOUR_OF_DAY),calNextDay.get(Calendar.MINUTE)); //day month year hour minute
        datetime_selected_end = date_selected + " " + time_selected;
        selected_end_date.setText(datetime_selected_end);
    }

    //takes a year and checks if it is a leap year (i.e. if February has 29 days)
    public Boolean checkLeapYear(int year) {

        return false;
    }

    //takes the month and checks if it is february (i.e. if month has 29 days)
    public Boolean checkMonthIfFebruary(int month) {

        return false;
    }

    public Boolean checkDaysInMonth(int month) {

        return false;
    }

    public int checkActualDaysInMonth(int year, int month) {
        Calendar cal = new GregorianCalendar(year, month, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.add_timer_button) {
                addTimerToDB();
            }
        }
    };

    public void addTimerToDB() {
        databaseHelper = DatesDatabaseHelper.getInstance(getContext());
        DateFormat df_selection = new SimpleDateFormat("dd MM yyyy HH:mm");

//        String date_time_start_string = date_selected_string + time_selected_string;
//        String date_time_end_string = date_selected_end_string + time_selected_end_string;

        Date date_datetime_selected;
        Date date_datetime_selected_end;
        String date_time_start_string = date_selected + " " + time_selected;
        String date_time_end_string = this.date_selected_end + " " + time_selected_end;

        Log.d("start string", "" + date_time_start_string);
        Log.d("end string", "" + date_time_end_string);


        try {
            date_datetime_selected = df_selection.parse(datetime_selected);
            date_datetime_selected_end = df_selection.parse(datetime_selected_end);
//            date_selected_start = df_selection.parse(date_time_start_string); //MMddyyyyHHmm
//            date_selected_end = df_selection.parse(date_time_end_string); //MMddyyyyHHmm
        } catch (ParseException e) {
            //input is incorrect
            Log.d("Parse error selection", String.valueOf(e));
            //error inputting please try again!
            return;
        }

        Log.d("start selection frag", "" + date_datetime_selected);
        Log.d("end selection frag", "" + date_datetime_selected_end);

        DateFormat df_month_input = new SimpleDateFormat("MM");
        DateFormat df_month_output = new SimpleDateFormat("MMM dd yyyy HH:mm");

        TimerDisplay timer = new TimerDisplay();
        timer.setStartDateTime(df_month_output.format(date_datetime_selected));
        timer.setEndDateTime(df_month_output.format(date_datetime_selected_end));
        //timer.setCurrentDate(df_month_output.format(date_selected_start)+ " " + dayPicker.getValue() + " " + yearPicker.getValue() + " 00:00");
        //timer.setEndDate(df_month_output.format(date_selected_end)+ " " + dayPickerEnd.getValue() + " " + yearPickerEnd.getValue() + " 12:00");
        timer.setPercentage();

        databaseHelper.addDate(timer);
        //listTimers.add(timer);
        ((MainActivity)getActivity()).returnToMainAndReload();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_start_date_number_picker: //Add Start Date by Number Picker
                showNumberPickerDialog(true, false);
                break;
            case R.id.add_start_date_calendar: //Add Start Date by Calendar
                showDatePickerDialog(true, false);
                break;
            case R.id.add_start_time: //Add Start Time by Clock
                showTimePickerDialog(true, false);
                break;
            case R.id.add_end_date_number_picker: //Add End Date by Number Picker
                showNumberPickerDialog(false, true);
                break;
            case R.id.add_end_date_calendar: //Add End Date by Calendar
                showDatePickerDialog(false, true);
                break;
            case R.id.add_end_time: //Add End Time by Clock
                showTimePickerDialog(false, true);
                break;
            case R.id.add_timer_button: //Add timer to DB
                addTimerToDB();
                break;
            default:
                break;
        }
    }

    public void showNumberPickerDialog(boolean target_start_date, boolean target_end_date){
        edit_start_date = target_start_date;
        edit_end_date = target_end_date;
//        if(target_start_date)
//            edit_start_date = true;
//        if(target_end_date)
//            edit_end_date = true;
        DialogFragmentNumberPicker dialogFragment = DialogFragmentNumberPicker.newInstance(this, this);
        dialogFragment.show(getActivity().getSupportFragmentManager(),"numberPicker");
    }

    public void showTimePickerDialog(boolean target_start_date, boolean target_end_date){
        edit_start_date = target_start_date;
        edit_end_date = target_end_date;
        DialogFragmentTimePicker dialogFragment = DialogFragmentTimePicker.newInstance(this);
        dialogFragment.show(getActivity().getSupportFragmentManager(),"timePicker");
    }

    public void showDatePickerDialog(boolean target_start_date, boolean target_end_date){
        edit_start_date = target_start_date;
        edit_end_date = target_end_date;
        DialogFragmentDatePicker dialogFragment = DialogFragmentDatePicker.newInstance(this);
        dialogFragment.show(getActivity().getSupportFragmentManager(),"datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(getActivity(),"Selected: " + year + " " + month + " " + dayOfMonth, Toast.LENGTH_LONG).show();
        if(edit_start_date) {
            date_selected = String.format(Locale.ENGLISH, "%02d", dayOfMonth) + " " + StringIntFormatter.formatIntToString(month, 2) + " " + String.format(Locale.ENGLISH, "%04d", year);
            String text_display = date_selected + " " + time_selected;
            selected_start_date.setText(text_display);
        } else if(edit_end_date) {
            date_selected_end = String.format(Locale.ENGLISH, "%02d", dayOfMonth) + " " + StringIntFormatter.formatIntToString(month, 2) + " " + String.format(Locale.ENGLISH, "%04d", year);
            String text_display = date_selected_end + " " + time_selected_end;
            selected_end_date.setText(text_display);
        }
        edit_end_date = false;
        edit_start_date = false;
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        Toast.makeText(getActivity(),"Selected: " + hour + " " + minute, Toast.LENGTH_LONG).show();
        if(edit_start_date) {
            time_selected = StringIntFormatter.formatIntToString(hour, 2) + ":" + StringIntFormatter.formatIntToString(minute, 2);
            String text_display = date_selected + " " + time_selected;
            selected_start_date.setText(text_display);
        } else if(edit_end_date) {
            time_selected_end = StringIntFormatter.formatIntToString(hour, 2) + ":" + StringIntFormatter.formatIntToString(minute, 2);
            String text_display = date_selected_end + " " + time_selected_end;
            selected_end_date.setText(text_display);
        }
        edit_end_date = false;
        edit_start_date = false;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getActivity(), StringIntFormatter.formatIntToString(yearPicker.getValue(),4) + StringIntFormatter.formatIntToString(monthPicker.getValue(),2) + StringIntFormatter.formatIntToString(dayPicker.getValue(),2)
                + " - " + hourPicker.getValue() + ":" + minutePicker.getValue(), Toast.LENGTH_SHORT).show();
        time_selected = StringIntFormatter.formatIntToString(hourPicker.getValue(), 2) + ":" + StringIntFormatter.formatIntToString(minutePicker.getValue(), 2);
        date_selected = StringIntFormatter.formatIntToString(yearPicker.getValue(),4) + StringIntFormatter.formatIntToString(monthPicker.getValue(),2) + StringIntFormatter.formatIntToString(dayPicker.getValue(),2);
        String text_display = date_selected + " " + time_selected;
        selected_start_date.setText(text_display);
    }

    /**
     * onDateTimeSet from DialogFragmentNumberPicker.OnDateTimeSelectedListener implementation that will be used when
     * creating an instance of DialogFragmentNumberPicker to get data from the dialog to fragment activity.
     * @param day Day of Month selected on NumberPicker
     * @param month Month selected on NumberPicker
     * @param year Year selected on NumberPicker
     * @param hour Hour selected on NumberPicker
     * @param minute Minute selected on NumberPicker
     */
    @Override
    public void onDateTimeSet(int day, int month, int year, int hour, int minute) {
        Toast.makeText(getActivity(), "Date received!", Toast.LENGTH_LONG).show();
        if(edit_start_date) {
            date_selected =  StringIntFormatter.formatIntToString(day,2) + " " + StringIntFormatter.formatIntToString(month,2) + " " + StringIntFormatter.formatIntToString(year,4);
            time_selected = StringIntFormatter.formatIntToString(hour, 2) + ":" + StringIntFormatter.formatIntToString(minute, 2);
            String text_display = date_selected + " " + time_selected;
            selected_start_date.setText(text_display);
        } else if(edit_end_date) {
            date_selected_end = StringIntFormatter.formatIntToString(day,2) + " " + StringIntFormatter.formatIntToString(month,2) + " " + StringIntFormatter.formatIntToString(year,4);
            time_selected_end = StringIntFormatter.formatIntToString(hour, 2) + ":" + StringIntFormatter.formatIntToString(minute, 2);
            String text_display = date_selected_end + " " + time_selected_end;
            selected_end_date.setText(text_display);
        }
        edit_end_date = false;
        edit_start_date = false;
    }

//    /**
//     * Method to convert integer into a string with correct number of digits, e.g. integer 24, digits 4 => 0024
//     * @param integer The integer value input to convert to string
//     * @param digits The number of digit placeholders needed in string
//     * @return String of the integer with correct formatting
//     */
//    private String formatIntToString(int integer, int digits) {
//        if(digits != 0) {
//            return String.format(Locale.ENGLISH, "%0" + digits + "d", integer);
//        } else {
//            return String.valueOf(integer);
//        }
//    }

    public void reloadFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(this);
        fragmentTransaction.attach(this);
        fragmentTransaction.commit();
    }

    private class TimerDisplaySelected {
        public String date = "MMMM dd YYYY";
        public String time = "HH:mm";
    }

}
