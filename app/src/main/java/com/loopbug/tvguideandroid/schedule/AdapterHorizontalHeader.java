package com.loopbug.tvguideandroid.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopbug.tvguideandroid.R;

import java.util.ArrayList;

/**
 * Created by joaoluis on 21/05/15.
 */
public class AdapterHorizontalHeader extends ArrayAdapter<String> {

    public static final String TAG = "AHS";

    private ArrayList<String> items;
    private Context mContext;
    private int resLayout;

    public AdapterHorizontalHeader(Context mContext, int textViewResourceId, ArrayList<String> items) {
        super(mContext, textViewResourceId, items);
        this.mContext = mContext;
        this.items = items;
        this.resLayout = textViewResourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(resLayout, parent, false);
            holder = new ViewHolder();
            holder.position = position;
            holder.hour = (TextView)convertView.findViewById(R.id.txt_tv_schedule_hour);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String o = items.get(position);
        if(o != null){
            holder.hour.setText(o);
        }
        return convertView;
    }

    private static class ViewHolder {
        int position;
        TextView hour;
    }

}