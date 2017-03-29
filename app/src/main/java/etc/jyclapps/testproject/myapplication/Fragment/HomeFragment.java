package etc.jyclapps.testproject.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class HomeFragment extends Fragment{

    public static CurrentTimerFragment newInstance() {
        CurrentTimerFragment fragment = new CurrentTimerFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false);

    }


}
