package etc.jyclapps.testproject.myapplication.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class DialogFragmentKeyInput extends DialogFragment {

    public static TimePickerDialog.OnTimeSetListener onTimeSetListener;

    public static DialogFragmentKeyInput newInstance(TimePickerDialog.OnTimeSetListener listener) {
        onTimeSetListener = listener;
        DialogFragmentKeyInput fragment = new DialogFragmentKeyInput();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        Dialog dialog = new TimePickerDialog(getActivity(),onTimeSetListener,hour, minute, true);
        return dialog;
        //return super.onCreateDialog(savedInstanceState);
        //implementing TimePickerDialog example:
        //https://developer.android.com/guide/topics/ui/controls/pickers.html
    }
}
