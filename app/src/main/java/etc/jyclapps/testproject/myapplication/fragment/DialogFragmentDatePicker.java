package etc.jyclapps.testproject.myapplication.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class DialogFragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static DatePickerDialog.OnDateSetListener onDateSetListener;

    public static DialogFragmentDatePicker newInstance(DatePickerDialog.OnDateSetListener listener) { //(DatePickerDialog.OnDateSetListener listener) {
        onDateSetListener = listener;
        DialogFragmentDatePicker fragment = new DialogFragmentDatePicker();
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
        int year = cal.get(Calendar.YEAR);
        Dialog dialog = new DatePickerDialog(getActivity(),this, year, month, day);
        return dialog;
        //return super.onCreateDialog(savedInstanceState);
        //implementing TimePickerDialog example:
        //https://developer.android.com/guide/topics/ui/controls/pickers.html
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(getActivity(),"Selected: " + year + " " + month + " " + dayOfMonth, Toast.LENGTH_LONG).show();
    }
}
