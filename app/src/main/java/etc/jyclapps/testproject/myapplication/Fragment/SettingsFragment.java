package etc.jyclapps.testproject.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Josh 2 on 1/14/2017.
 */

public class SettingsFragment extends Fragment{

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        TextView contactInfo = (TextView) view.findViewById(R.id.contact_info_text);
        String contactInfoText = getResources().getString(R.string.settings_contact_info) + " " + getResources().getString(R.string.settings_email_address);
        contactInfo.setText(contactInfoText);
        //ensure the links are HTMLs by setting layout android attribute autoLink or adding HTML text <a href=\"...\">hyperlink</a>
        //set the TextView with the following lines to be able to click links (need to add HTML)
        contactInfo.setClickable(true);
        contactInfo.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }


}
