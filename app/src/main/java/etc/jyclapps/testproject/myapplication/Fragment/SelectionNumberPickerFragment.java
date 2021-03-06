package etc.jyclapps.testproject.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import etc.jyclapps.testproject.myapplication.activity.MainActivity;
import etc.jyclapps.testproject.myapplication.helper.DatesDatabaseHelper;
import etc.jyclapps.testproject.myapplication.model.TimerDisplay;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class SelectionNumberPickerFragment extends Fragment { //DatePicker

    DatesDatabaseHelper databaseHelper;

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

    private String monthSelected;
    private String daySelected;
    private String yearSelected;

    private String hourSelected;
    private String minuteSelected;
    private String secondSelected;


    /**
     * SelectionFragment constructor called by SelectionTypeAdapter (to display various input types)
     * @return SelectionFragment
     */
    public static SelectionNumberPickerFragment newInstance() {
        SelectionNumberPickerFragment fragment = new SelectionNumberPickerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.selection_fragment, container, false); //example http://stackoverflow.com/questions/6495898/


        initializeView(view);

        // Add Timer Button submits the selected timer dates into the DB
        Button addTimerButton = (Button) view.findViewById(R.id.add_timer_button); //        View.OnClickListener
        addTimerButton.setOnClickListener(buttonClickListener);

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        DateFormat df_month = new SimpleDateFormat("MM");
        DateFormat df_year = new SimpleDateFormat("yyyy");
        DateFormat df_day = new SimpleDateFormat("dd");
        DateFormat df_month_text = new SimpleDateFormat("MMM");

        DateFormat df_hour = new SimpleDateFormat("HH");
        DateFormat df_min = new SimpleDateFormat("mm");

        //times (hour and minutes)
        hourPicker = (NumberPicker) view.findViewById(R.id.hour_number_picker);
        hourPickerEnd = (NumberPicker) view.findViewById(R.id.hour_number_picker_end);

        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
        hourPicker.setValue(Integer.parseInt(df_hour.format(date)));

        hourPickerEnd.setMaxValue(23);
        hourPickerEnd.setMinValue(0);
        hourPickerEnd.setValue(Integer.parseInt(df_hour.format(date)));

        minutePicker = (NumberPicker) view.findViewById(R.id.minute_number_picker);
        minutePickerEnd = (NumberPicker) view.findViewById(R.id.minute_number_picker_end);

        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        minutePicker.setValue(Integer.parseInt(df_min.format(date)));

        minutePickerEnd.setMaxValue(59);
        minutePickerEnd.setMinValue(0);
        minutePickerEnd.setValue(Integer.parseInt(df_min.format(date)));


        //start date
        dayPicker = (NumberPicker) view.findViewById(R.id.day_number_picker);
        dayPickerEnd = (NumberPicker) view.findViewById(R.id.day_number_picker_end);

        dayPicker.setMaxValue(31);
        dayPicker.setMinValue(1);
        dayPicker.setValue(Integer.parseInt(df_day.format(date)));

        dayPickerEnd.setMaxValue(31);
        dayPickerEnd.setMinValue(1);
        dayPickerEnd.setValue(Integer.parseInt(df_day.format(date)));

        monthPicker = (NumberPicker) view.findViewById(R.id.month_number_picker);
        monthPickerEnd = (NumberPicker) view.findViewById(R.id.month_number_picker_end);

        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);
        monthPicker.setValue(Integer.parseInt(df_month.format(date)));

        monthPickerEnd.setMaxValue(12);
        monthPickerEnd.setMinValue(1);
        monthPickerEnd.setValue(Integer.parseInt(df_month.format(date)));

        yearPicker = (NumberPicker) view.findViewById(R.id.year_number_picker);
        yearPickerEnd = (NumberPicker) view.findViewById(R.id.year_number_picker_end);

        //yearPicker.onTouchEvent()
        yearPicker.setMaxValue(2099);
        yearPicker.setMinValue(1901);
        yearPicker.setValue(Integer.parseInt(df_year.format(date)));

        yearPickerEnd.setMaxValue(2099);
        yearPickerEnd.setMinValue(1901);
        yearPickerEnd.setValue(Integer.parseInt(df_year.format(date)));

        return view;

    }

    /**
     * Method called to initialize all the views and set the layout display
     * @param view
     */
    private void initializeView(View view) {

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
        DateFormat df_selection = new SimpleDateFormat("MMddyyyyHHmm");

        String date_selected_string = String.valueOf(String.format(Locale.ENGLISH, "%02d", monthPicker.getValue()) + String.format(Locale.ENGLISH, "%02d", dayPicker.getValue()) + String.format(Locale.ENGLISH, "%04d", yearPicker.getValue()));
        String date_selected_end_string = String.valueOf(String.format(Locale.ENGLISH, "%02d", monthPickerEnd.getValue()) + String.format(Locale.ENGLISH, "%02d", dayPickerEnd.getValue()) + String.format(Locale.ENGLISH, "%04d", yearPickerEnd.getValue()));

        String time_selected_string = String.valueOf(String.format(Locale.ENGLISH, "%02d", hourPicker.getValue()) + String.format(Locale.ENGLISH, "%02d", minutePicker.getValue()));
        String time_selected_end_string = String.valueOf(String.format(Locale.ENGLISH, "%02d", hourPickerEnd.getValue()) + String.format(Locale.ENGLISH, "%02d", minutePickerEnd.getValue()));

        //String date_selected_end_string = String.valueOf(monthPickerEnd.getValue()) + dayPickerEnd.getValue() + yearPickerEnd.getValue();
        Log.d("start string", "" + date_selected_string + time_selected_string);
        Log.d("end string", "" + date_selected_end_string + time_selected_end_string);

        String date_time_start_string = date_selected_string + time_selected_string;
        String date_time_end_string = date_selected_end_string + time_selected_end_string;

        Date date_selected_start = new Date();
        Date date_selected_end = new Date();

        try {
            date_selected_start = df_selection.parse(date_time_start_string); //MMddyyyyHHmm
            date_selected_end = df_selection.parse(date_time_end_string); //MMddyyyyHHmm
        } catch (ParseException e) {
            //input is incorrect
            Log.d("Parse error selection", String.valueOf(e));
            //error inputting please try again!
            return;
        }

        Log.d("start selection frag", "" + date_selected_start);
        Log.d("end selection frag", "" + date_selected_end);

        DateFormat df_month_input = new SimpleDateFormat("MM");
        DateFormat df_month_output = new SimpleDateFormat("MMM dd yyyy HH:mm");

        TimerDisplay timer = new TimerDisplay();
        timer.setStartDateTime(df_month_output.format(date_selected_start));
        timer.setEndDateTime(df_month_output.format(date_selected_end));
        //timer.setCurrentDate(df_month_output.format(date_selected_start)+ " " + dayPicker.getValue() + " " + yearPicker.getValue() + " 00:00");
        //timer.setEndDate(df_month_output.format(date_selected_end)+ " " + dayPickerEnd.getValue() + " " + yearPickerEnd.getValue() + " 12:00");
        timer.setPercentage();

        databaseHelper.addDate(timer);
        //listTimers.add(timer);
        ((MainActivity)getActivity()).returnToMainAndReload();
    }

}
