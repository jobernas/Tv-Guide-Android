package com.loopbug.tvguideandroid.schedule;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopbug.tvguideandroid.Channel;
import com.loopbug.tvguideandroid.MainActivity;
import com.loopbug.tvguideandroid.ManagerTime;
import com.loopbug.tvguideandroid.R;
import com.meetme.android.horizontallistview.HorizontalListView;

import java.util.ArrayList;

/**
 * Created by joaoluis on 21/05/15.
 */
public class AdapterVerticalSchedule extends ArrayAdapter<Channel> {

    public static final String TAG = "ADAP_FULL_SCHEDULE";

    private ArrayList<Channel> items;
    private Context mContext;
    private int resLayout;
    private long lastMilliOfDay = 0;
    private SyncedHorizontalScrollListener mSyncedHorizontalScrollListener;

    public AdapterVerticalSchedule(Context mContext, int textViewResourceId, ArrayList<Channel> items){
        super(mContext, textViewResourceId, items);
        this.mContext = mContext;
        this.items = items;
        this.resLayout = textViewResourceId;
        this.mSyncedHorizontalScrollListener = new SyncedHorizontalScrollListener(mContext);
        try{
            this.lastMilliOfDay = ManagerTime.getLastMilliOfDay(MainActivity.date);
        }catch (Exception e){
            Log.e(TAG, "Error:", e);
            this.lastMilliOfDay = 0;
        }
    }

    public void addHorizontalScrollView(HorizontalListView mHorizontalScrollView){
        this.mSyncedHorizontalScrollListener.addView(mHorizontalScrollView);
    }

    public SyncedHorizontalScrollListener getSyncedHorizontalScrollListener(){
        return this.mSyncedHorizontalScrollListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        parent.requestDisallowInterceptTouchEvent(true);
        final Channel o = items.get(position);

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resLayout, parent, false);
            holder = new ViewHolder();
            holder.position = position;
            holder.scheduleList = (HorizontalListView)convertView.findViewById(R.id.hlv_schedule_row);
            holder.channelName = (TextView)convertView.findViewById(R.id.txt_channel_name);

            //Add View to Synced Listener
            holder.scheduleList.setTag(position);
            mSyncedHorizontalScrollListener.addView(holder.scheduleList);
            holder.scheduleList.setOnScrollStateChangedListener(mSyncedHorizontalScrollListener);
            holder.scheduleList.setOnTouchListener(mSyncedHorizontalScrollListener);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(o != null){
            final AdapterHorizontalSchedule mHorizontalFullSchedule = new AdapterHorizontalSchedule(mContext, R.layout.list_item_schedule, o.getSchedule());
            holder.scheduleList.setAdapter(mHorizontalFullSchedule);
            holder.scheduleList.scrollTo(mSyncedHorizontalScrollListener.getScrollX());
            holder.channelName.setText(o.getName());
        }

        return convertView;
    }

    private static class ViewHolder {
        int position;
        HorizontalListView scheduleList;
        TextView channelName;
    }
}