package etc.jyclapps.testproject.myapplication.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import etc.jyclapps.testproject.myapplication.fragment.CurrentTimerFragment;
import etc.jyclapps.testproject.myapplication.fragment.HomeFragment;
import etc.jyclapps.testproject.myapplication.fragment.SelectionFragment;
import etc.jyclapps.testproject.myapplication.fragment.SettingsFragment;
import etc.jyclapps.testproject.myapplication.R;

public class MainActivity extends AppCompatActivity {

    //private static final int CONTAINER_VIEW_ID = 101010;
    private String fragmentID = "fragment_";
    private int fragmentCount = 0;
    boolean hideIcon = false;


    //set Broadcast Receiver
    //set Background Service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FrameLayout frame = new FrameLayout(this);
//        frame.setId(CONTAINER_VIEW_ID);
        setContentView(R.layout.activity_main);

        //set up toolbar
        //https://developer.android.com/training/appbar/setting-up.html
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //.setTitle("Current Schedules");


        //HomeFragment fragment = new HomeFragment();
        CurrentTimerFragment fragment = new CurrentTimerFragment();
        showFragment(fragment);

    }

    /**
     * Inflate toolbar menu with custom icons.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        if(hideIcon) {
            menu.findItem(R.id.action_add_new_schedule).setVisible(false);
        } else {
            menu.findItem(R.id.action_add_new_schedule).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_schedule:
                hideIcon = true;
                invalidateOptionsMenu();
                openSelectionFragment();
                return true;

            case R.id.action_go_to_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                hideIcon = true;
                invalidateOptionsMenu();
                openSettingsFragment();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

//
//    public void onClickButton() {
//        CurrentTimerFragment fragment = new CurrentTimerFragment();
//        showFragment(fragment);
//    }


    //show fragment where user can select start and end dates for timer (onClick Function)
    public void openSelectionFragment(View view) {
        SelectionFragment fragment = new SelectionFragment();
        showFragment(fragment); //Note: ensure you have selected v4.app.Fragment
    }

    //show fragment where user can select start and end dates for timer (programmatically method call)
    public void openSelectionFragment() {
        SelectionFragment fragment = new SelectionFragment();
        showFragment(fragment); //Note: ensure you have selected v4.app.Fragment
    }

    public void openSettingsFragment() {
        SettingsFragment fragment = new SettingsFragment();
        showFragment(fragment); //Note: ensure you have selected v4.app.Fragment
    }

    public void showHomeFragment() {
        HomeFragment fragment = new HomeFragment();
        showFragment(fragment); //Note: ensure you have selected v4.app.Fragment
    }

    public void openTimerFragment(View view) {
        CurrentTimerFragment fragment = new CurrentTimerFragment();
        showFragment(fragment); //Note: ensure you have selected v4.app.Fragment
    }


    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Log.d("", "fragment being added: " + fragmentID + fragmentCount);
        String fragmentTag = fragmentID + fragmentCount;
        //fragmentTransaction.addToBackStack("");
        fragmentTransaction.replace(R.id.fragment_main, fragment, fragmentTag).addToBackStack("");
//        fragmentTransaction.add(((ViewGroup)getView().getParent()).getId(), fragment);
        fragmentTransaction.commit();
        fragmentCount++;
    }

    public void returnToMainAndReload() {
        Log.d("", "return to ma                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             1in and reloading fragment");
        fragmentCount--;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate();
        //reloadFragment();
    }

//    public void reloadFragment() {
//        Log.d("", "reloading fragment");
//        Log.d("", "fragment being reloaded: " + fragmentID + fragmentCount);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        CurrentTimerFragment currentFragment = (CurrentTimerFragment) fragmentManager.findFragmentByTag(fragmentID + fragmentCount);
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.detach(currentFragment);
//        fragmentTransaction.attach(currentFragment);
//
//        fragmentTransaction.commit();
//    }


    @Override
    public void onBackPressed() {
        hideIcon = false; //on back ensure that add new timer can be seen
        fragmentCount--;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getFragments() != null && fragmentManager.getBackStackEntryCount() > 0) {
            //TODO
            //check if fragment is selectionfragment, if not, set hideIcon to false
            Log.d("", "back press scenario");
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
            //finish();
        }
        //showHomeFragment();
    }

    //create
    //fragment formed when pressing button
    //fragment forms a clock timer on it of todays date
    // main layout
    // - add timer (from a certain period)
    // - check current time
    // - enquire time until designated date*** start with this one
    // --e.g. input date calculates timer from current time

}
