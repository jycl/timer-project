package etc.jyclapps.testproject.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import etc.jyclapps.testproject.myapplication.tools.StringIntFormatter;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class DialogFragmentNumberPicker extends DialogFragment implements NumberPicker.OnValueChangeListener {

    private NumberPicker monthPicker;
    private NumberPicker dayPicker;
    private NumberPicker yearPicker;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;

    public String selectedString;

    public static DialogInterface.OnClickListener onClickListener;
    public static OnDateTimeSelectedListener onDateTimeSelectedListener;

    //public static TimePickerDialog.OnTimeSetListener onTimeSetListener;

    public static DialogFragmentNumberPicker newInstance(DialogInterface.OnClickListener listener, OnDateTimeSelectedListener listener2) {//(TimePickerDialog.OnTimeSetListener listener) {
        onClickListener = listener;
        onDateTimeSelectedListener = listener2;
        DialogFragmentNumberPicker fragment = new DialogFragmentNumberPicker();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month += 1; //month in Calendar returns 0-11, must add one to correct to 1-12
        int year = cal.get(Calendar.YEAR);

//        Dialog dialog_num_picker = new Dialog(getActivity());
//        dialog_num_picker.setContentView(R.layout.dialog_number_picker);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //View dialog_num_picker = inflater.inflate(R.layout.selection_fragment_start, null);


        Dialog dialog_num_picker = builder.setView(inflater.inflate(R.layout.dialog_number_picker, null))
            .setPositiveButton(R.string.set_date, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), StringIntFormatter.formatIntToString(yearPicker.getValue(),4) + StringIntFormatter.formatIntToString(monthPicker.getValue(),2) + StringIntFormatter.formatIntToString(dayPicker.getValue(),2)
                        + " - " + hourPicker.getValue() + ":" + minutePicker.getValue(), Toast.LENGTH_SHORT).show();
                String date = StringIntFormatter.formatIntToString(yearPicker.getValue(),4) + " "
                        + StringIntFormatter.formatIntToString(monthPicker.getValue(),2) + " "
                        + StringIntFormatter.formatIntToString(dayPicker.getValue(),2);
                String time = StringIntFormatter.formatIntToString(hourPicker.getValue(),2) + ":" + StringIntFormatter.formatIntToString(minutePicker.getValue(),2);
                onDateTimeSelectedListener.onDateTimeSet(dayPicker.getValue(),monthPicker.getValue(),yearPicker.getValue(),hourPicker.getValue(),minutePicker.getValue());
                }
            })
            //.setPositiveButton(R.string.set_date, onClickListener)
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DialogFragmentNumberPicker.this.getDialog().cancel();
                }
            }).create();

        //in the case of using AlertDialog.Builder, make sure you invoke .show() before findViewById otherwise NullPointerException will be thrown
        //http://stackoverflow.com/questions/35808145/
        dialog_num_picker.show();
        //Dialog dialog_num_picker = builder.create();

        hourPicker = (NumberPicker) dialog_num_picker.findViewById(R.id.hour_number_picker);
        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
        hourPicker.setValue(hour);
        //hourPicker.setOnValueChangedListener(this);

        minutePicker = (NumberPicker) dialog_num_picker.findViewById(R.id.minute_number_picker);
        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        minutePicker.setValue(minute);
        //minutePicker.setOnValueChangedListener(this);

        dayPicker = (NumberPicker) dialog_num_picker.findViewById(R.id.day_number_picker);
        dayPicker.setMaxValue(31);
        dayPicker.setMinValue(1);
        dayPicker.setValue(day);
        //dayPicker.setOnValueChangedListener(this);

        monthPicker = (NumberPicker) dialog_num_picker.findViewById(R.id.month_number_picker);
        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);
        monthPicker.setValue(month);
        monthPicker.setOnValueChangedListener(this);

        yearPicker = (NumberPicker) dialog_num_picker.findViewById(R.id.year_number_picker);
        yearPicker.setMaxValue(2099);
        yearPicker.setMinValue(1901);
        yearPicker.setValue(year);
        yearPicker.setOnValueChangedListener(this);



        //Dialog dialog = new TimePickerDialog(getActivity(),onTimeSetListener,hour, minute, true);
        return dialog_num_picker;
        //return super.onCreateDialog(savedInstanceState);
        //implementing TimePickerDialog example:
        //https://developer.android.com/guide/topics/ui/controls/pickers.html
    }


    //Creating event callbacks to the activity
    //https://developer.android.com/guide/components/fragments.html#Lifecycle
    public interface OnDateTimeSelectedListener extends DialogInterface.OnClickListener {
        void onDateTimeSet(int day, int month, int year, int hour, int minute);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
         //2017-04-15 added a checking onValueChange to ensure that the number of days corresponds correctly to the month
         //use switch/case (picker.getId()) if more than selections otherwise use else if for the time being
         if(picker.equals(monthPicker)) {
             //int day = dayPicker.getValue();
             int month = newVal - 1; //months begin with zero in Calendar, so input must be (1-12) - 1 = (0-11)
             int year = yearPicker.getValue();
             //Using selected month and year on value change, change number of days accordingly
             //http://stackoverflow.com/questions/8940438/

             Calendar cal = new GregorianCalendar(year, month, 1);
             dayPicker.setMaxValue(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

         } else if (picker.equals(yearPicker)) {
             int month = monthPicker.getValue() - 1; //months begin with zero in Calendar
             int year = newVal;

             Calendar cal = new GregorianCalendar(year, month, 1);
             dayPicker.setMaxValue(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
         }
     }

//    public void setUpNumberPickerView(NumberPicker picker, View view, int max, int min) {
//        hourPicker = (NumberPicker) view.findViewById(R.id.hour_number_picker);
//        hourPickerEnd = (NumberPicker) view.findViewById(R.id.hour_number_picker_end);
//
//        hourPicker.setMaxValue(23);
//        hourPicker.setMinValue(0);
//        hourPicker.setValue(Integer.parseInt(df_hour.format(date)));
//    }
}
