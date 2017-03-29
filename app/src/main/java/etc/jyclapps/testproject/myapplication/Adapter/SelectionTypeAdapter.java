package etc.jyclapps.testproject.myapplication.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import etc.jyclapps.testproject.myapplication.Fragment.SelectionFragment;
import etc.jyclapps.testproject.myapplication.Fragment.SelectionCalendarFragment;
import etc.jyclapps.testproject.myapplication.Model.TimerDisplay;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Joshua YC Leung on 1/13/2017.
 * SelectionTypeAdapter extends PagerAdapter so that the user can select the method of input he/she wants to use for entering start/end datetime(s)
 */

public class SelectionTypeAdapter extends FragmentPagerAdapter {
    private Context context;
    private static int NUM_SELECT_TYPES = 2;

    public SelectionTypeAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return NUM_SELECT_TYPES;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return SelectionFragment.newInstance();
            case 1:
                return SelectionCalendarFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.selection_picker_title);
            case 1:
                return context.getResources().getString(R.string.selection_calendar_title);
            default:
                return "Other";
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
