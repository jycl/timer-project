package etc.jyclapps.testproject.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Joshua YC Leung on 1/13/2017.
 */

public class RowListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList= new ArrayList<>(); //make sure to initialize the array list i.e. new ArrayList


    public RowListAdapter(Context context) {
        this.context = context;
        arrayList.add(0, "Time_0");
        arrayList.add(1, "Time_1"); //start at 0, invalid index 1 size is 0
        arrayList.add(2, "Time_2");
        arrayList.add(3, "Time_3");
        arrayList.add(4, "Time_4");

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.timer_row_layout_custom, parent, false);
        }

        TextView textViewTime = (TextView) convertView.findViewById(R.id.time_digital);
        textViewTime.setText(arrayList.get(position));


        return convertView;
    }
}
