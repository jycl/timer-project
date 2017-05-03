package etc.jyclapps.testproject.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import etc.jyclapps.testproject.myapplication.fragment.SelectionFragment;
import etc.jyclapps.testproject.myapplication.fragment.SelectionCalendarFragment;
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
