package etc.jyclapps.testproject.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.util.TypedValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import etc.jyclapps.testproject.myapplication.model.TimerDisplay;
import etc.jyclapps.testproject.myapplication.R;

/**
 * Created by Joshua YC Leung on 1/13/2017.
 */

public class TimerHomeDisplayRowAdapter extends BaseAdapter {
    private Context context;
    //private ArrayList<String> arrayList= new ArrayList<>(); //make sure to initialize the array list i.e. new ArrayList
    private ArrayList<TimerDisplay> listTimers = new ArrayList<>(); //make sure to initialize the array list i.e. new ArrayList


    public TimerHomeDisplayRowAdapter(Context context, ArrayList<TimerDisplay> list) {
        this.context = context;
        this.listTimers = list;
//        arrayList.add(0, "Time_0");
//        arrayList.add(1, "Time_1"); //start at 0, invalid index 1 size is 0
//        arrayList.add(2, "Time_2");
//        arrayList.add(3, "Time_3");
//        arrayList.add(4, "Time_4");

    }

    @Override
    public int getCount() {
        return listTimers.size();
    }

    @Override
    public Object getItem(int position) {
        return listTimers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.timer_row_layout_home_display, parent, false);
        }

        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");

        Date current_date = new Date();
        Date start_date = new Date();
        Date end_date = new Date();

        Log.d("List timer size", "" + listTimers.size()); //check if any inputs into DB

        try{
            start_date = df.parse(listTimers.get(position).getStartDate());
            end_date = df.parse(listTimers.get(position).getEndDate());
//            start_date = df.parse(listTimers.get(position).getStartDateTime());
//            end_date = df.parse(listTimers.get(position).getEndDateTime());
        } catch (ParseException e) {

        }

        Log.d("dates:", "start date: " + start_date + " end date: " + end_date +  " current date: " + current_date);
        Log.d("dates:", "start sec: " + start_date.getTime() + " end sec: " + end_date.getTime() +  " current sec: " + current_date.getTime());

        double start_date_millis = start_date.getTime();
        double end_date_millis = end_date.getTime();
        double current_date_millis = current_date.getTime();

        //scenario in which end date = start date is accounted for
        double percentage = (start_date_millis != end_date_millis ? ((current_date_millis - start_date_millis)/(end_date_millis - start_date_millis)) * 100 : 100);
        Log.d(""," percentage: " + current_date);

//        Log.d("percentage top", String.valueOf(current_date.getTime() - start_date.getTime()));
//        Log.d("percentage bottom", String.valueOf(end_date.getTime() - start_date.getTime()));
//        Log.d("percentage both", String.valueOf((current_date.getTime() - start_date.getTime())/(end_date.getTime() - start_date.getTime())));
        double percentage_formatted = BigDecimal.valueOf(percentage).setScale(2, RoundingMode.HALF_UP).doubleValue();

        Log.d("percentage formatted", String.valueOf(percentage_formatted));

        Log.d("percentage", String.valueOf(percentage));
        int percentageInt = (int) percentage_formatted;
        Log.d("percentage int", String.valueOf(percentageInt));

        TextView textViewStartTime = (TextView) convertView.findViewById(R.id.time_start);
        textViewStartTime.setText(listTimers.get(position).getStartDate());

        TextView textViewEndTime = (TextView) convertView.findViewById(R.id.time_end);
        textViewEndTime.setText(listTimers.get(position).getEndDate());
        //TextView textViewTime = (TextView) convertView.findViewById(R.id.time_digital);
        //textViewTime.setText(listTimers.get(position));

        TextView textViewPercentage = (TextView) convertView.findViewById(R.id.percentage_time_passed);

        textViewPercentage.setText(String.valueOf(percentage_formatted));
        //MMM dd yyyy HH:mm

        View percentageFill = convertView.findViewById(R.id.analog_clock_image);

        int height = (percentageInt > 100) ? 100 : percentageInt;
        int width = 80;

        textViewPercentage.setText(String.valueOf(height));

        int height_dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, this.context.getResources().getDisplayMetrics());
        int width_dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, this.context.getResources().getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width_dp,height_dp);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80,50);
        percentageFill.setLayoutParams(layoutParams);

        return convertView;
    }

    public class DisplayHolder {

    }
}
