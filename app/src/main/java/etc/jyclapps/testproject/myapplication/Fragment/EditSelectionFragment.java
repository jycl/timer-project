package etc.jyclapps.testproject.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import etc.jyclapps.testproject.myapplication.activity.MainActivity;
import etc.jyclapps.testproject.myapplication.Helper.DatesDatabaseHelper;
import etc.jyclapps.testproject.myapplication.Model.TimerDisplay;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class EditSelectionFragment extends Fragment {

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
    //private NumberPicker hourPicker;


    private String monthSelected;
    private String daySelected;
    private String yearSelected;

    private String hourSelected;
    private String minuteSelected;
    private String secondSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.selection_fragment, container, false); //example http://stackoverflow.com/questions/6495898/

        initializeView(view);

        Button addTimerButton = (Button) view.findViewById(R.id.add_timer_button);
//        View.OnClickListener
        addTimerButton.setOnClickListener(buttonClickListener);

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();


        DateFormat df_month = new SimpleDateFormat("MM");
        DateFormat df_year = new SimpleDateFormat("yyyy");
        DateFormat df_day = new SimpleDateFormat("dd");
        DateFormat df_month_text = new SimpleDateFormat("MMM");


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

    private void initializeView(View view) {

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
        DateFormat df_selection = new SimpleDateFormat("MMddyyyy");
        String date_selected_string = String.valueOf(monthPicker.getValue()) + dayPicker.getValue() + yearPicker.getValue();
        String date_selected_end_string = String.valueOf(monthPickerEnd.getValue()) + dayPickerEnd.getValue() + yearPickerEnd.getValue();

        Date date_selected_start = new Date();
        Date date_selected_end = new Date();

        try {
            date_selected_start = df_selection.parse(date_selected_string); //MMddyyyy
            date_selected_end = df_selection.parse(date_selected_end_string); //MMddyyyy

        } catch (ParseException e) {
            //input is incorrect
        }

        DateFormat df_month_input = new SimpleDateFormat("MM");
        DateFormat df_month_output = new SimpleDateFormat("MMM");

        //Date date_month = df_month_input.parse()
        //String month_text = df_month_output.format(monthPicker.getValue());

        TimerDisplay timer = new TimerDisplay();
        timer.setCurrentDate(df_month_output.format(date_selected_start)+ " " + dayPicker.getValue() + " " + yearPicker.getValue() + " 00:00");
        timer.setEndDate(df_month_output.format(date_selected_end)+ " " + dayPickerEnd.getValue() + " " + yearPickerEnd.getValue() + " 12:00");
        timer.setPercentage();

        databaseHelper.addDate(timer);
        //listTimers.add(timer);
        ((MainActivity)getActivity()).returnToMainAndReload();
    }
}
