package etc.jyclapps.testproject.myapplication.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import etc.jyclapps.testproject.myapplication.Fragment.CurrentTimerFragment;
import etc.jyclapps.testproject.myapplication.Fragment.HomeFragment;
import etc.jyclapps.testproject.myapplication.Fragment.SelectionFragment;
import etc.jyclapps.testproject.myapplication.R;

public class MainActivity extends AppCompatActivity {

    //private static final int CONTAINER_VIEW_ID = 101010;
    private String fragmentID = "fragment_";
    private int fragmentCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FrameLayout frame = new FrameLayout(this);
//        frame.setId(CONTAINER_VIEW_ID);
        setContentView(R.layout.activity_main);

        //HomeFragment fragment = new HomeFragment();
        CurrentTimerFragment fragment = new CurrentTimerFragment();
        showFragment(fragment);

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
        Log.d("", "return to main and reloading fragment");
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
        fragmentCount--;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getFragments() != null && fragmentManager.getBackStackEntryCount() > 0) {
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
