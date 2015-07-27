package com.loopbug.tvguideandroid.schedule;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopbug.tvguideandroid.MainActivity;
import com.loopbug.tvguideandroid.ManagerTime;
import com.loopbug.tvguideandroid.R;
import com.loopbug.tvguideandroid.Schedule;
import com.meetme.android.horizontallistview.HorizontalListView;
import java.util.ArrayList;

/**
 * Created by joaoluis on 21/05/15.
 */
public class AdapterHorizontalSchedule extends ArrayAdapter<Schedule> {

    public static final String TAG = "AHS";

    private ArrayList<Schedule> items;
    private Context mContext;
    private int resLayout;
    private long lastMilliOfDay;

    public AdapterHorizontalSchedule(Context mContext, int textViewResourceId, ArrayList<Schedule> items) {
        super(mContext, textViewResourceId, items);
        this.mContext = mContext;
        this.items = items;
        this.resLayout = textViewResourceId;
        try{
            this.lastMilliOfDay = ManagerTime.getLastMilliOfDay(MainActivity.date);
        }catch (Exception e){
            Log.e(TAG, "Error", e);
            this.lastMilliOfDay = 0;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resLayout, parent, false);
            holder = new ViewHolder();
            holder.position = position;
            holder.container = (LinearLayout)convertView.findViewById(R.id.ll_schedule_item_container);
            holder.name = (TextView)convertView.findViewById(R.id.txt_schedule_item_name);
            holder.hour = (TextView)convertView.findViewById(R.id.txt_schedule_item_hour);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Schedule o = items.get(position);
        if(o != null){

            final HorizontalListView.LayoutParams mParams = holder.container.getLayoutParams();
            final int duration = (int)((lastMilliOfDay >= o.getEndHour()) ? o.getDuration() : ((lastMilliOfDay - o.getEndHour()) /(double)1000));
            final double timeRacio = ((double) duration / (double) 1800);
            mParams.width = (int)(mContext.getResources().getDimension(R.dimen.schedule_hour_width) * timeRacio);
            holder.container.setLayoutParams(mParams);

            if(o.isDummy()){
                holder.name.setVisibility(View.INVISIBLE);
                holder.hour.setVisibility(View.INVISIBLE);
                holder.container.setBackgroundResource(R.color.list_item_schedule_blank_background);
            }else{
                holder.name.setVisibility(View.VISIBLE);
                holder.hour.setVisibility(View.VISIBLE);
                holder.container.setBackgroundResource(R.drawable.ll_schedule_background);
                holder.hour.setText(ManagerTime.getStringHour(o.getStartHour()) + " " + ManagerTime.getStringHour(o.getEndHour()));
                holder.name.setText(o.getName());
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        int position;
        LinearLayout container;
        TextView name;
        TextView hour;
    }

}