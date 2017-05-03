package etc.jyclapps.testproject.myapplication.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import etc.jyclapps.testproject.myapplication.activity.MainActivity;
import etc.jyclapps.testproject.myapplication.adapter.TimerHomeDisplayRowAdapter;
import etc.jyclapps.testproject.myapplication.helper.DatesDatabaseHelper;
import etc.jyclapps.testproject.myapplication.model.TimerDisplay;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Joshua YC Leung on 1/9/2017.
 */

public class CurrentTimerFragment extends Fragment {

    //MainActivity mainActivity = (MainActivity) getActivity();
    ListView listView;
    TimerHomeDisplayRowAdapter rowListAdapter;
    ArrayList<TimerDisplay> listTimers = new ArrayList<>();
    ArrayList<TimerDisplay> listFromDB = new ArrayList<>();
    //RowListAdapter rowListAdapter;
    Context mContext;

    DatesDatabaseHelper databaseHelper;


    public static CurrentTimerFragment newInstance() {
        CurrentTimerFragment fragment = new CurrentTimerFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        databaseHelper = DatesDatabaseHelper.getInstance(this.mContext);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.timer_fragment, container, false);

        // Set buttons on top right corner (+) Add New Timer and (...) More Settings
        Button addTimerButton = (Button) view.findViewById(R.id.button_add_timer);
        addTimerButton.setOnClickListener(buttonClickListener);
        Button moreSettingsButton = (Button) view.findViewById(R.id.button_more_options);
        moreSettingsButton.setOnClickListener(buttonClickListener);


        //databaseHelper.deleteAllEntries();
        //addListDataToDB();
        //setTimerListData();

        retrieveListFromDB(); //retrieve list from DB to add to Adapter to create view

        rowListAdapter = new TimerHomeDisplayRowAdapter(mContext, listFromDB);
        //rowListAdapter = new TimerHomeDisplayRowAdapter(mContext, listTimers);
        //rowListAdapter = new RowListAdapter(mContext);

        listView = new ListView(mContext);

        listView = (ListView) view.findViewById(R.id.list_layout_timers);
        listView.setAdapter(rowListAdapter);

        return view;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button_add_timer) {
                MainActivity mActivity = (MainActivity) getActivity();
                mActivity.openSelectionFragment();
            } else if(v.getId() == R.id.button_more_options) {
                databaseHelper.deleteAllEntries();
            }
        }
    };

    public void retrieveListFromDB() {
        listFromDB = databaseHelper.getAllTimers();
    }

    /**
     * Test Case - Set List to Add to DB
     */
    public void setTimerListData() {
        for (int i = 0; i < 10; i++) {
            TimerDisplay timer = new TimerDisplay();
            timer.setCurrentDate("Jan 0" + i + " 2017 00:00");
            timer.setEndDate("Jan 0" + (i+1) + " 2017 00:00");
            timer.setPercentage();

            listTimers.add(timer);
        }
    }

    /**
     * Test Case - Add List to DB
     */
    public void addListDataToDB() {

        for (int i = 1; i < 10; i++) {
            TimerDisplay timer = new TimerDisplay();
            timer.setCurrentDate("Jan 0" + i + " 2017 00:00");
            timer.setEndDate("Jan 0" + (i + 2) + " 2017 00:00");
            timer.setPercentage();

            databaseHelper.addDate(timer);
            //listTimers.add(timer);
        }
    }
}
