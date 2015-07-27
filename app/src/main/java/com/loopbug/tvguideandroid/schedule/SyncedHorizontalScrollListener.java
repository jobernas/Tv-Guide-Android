package com.loopbug.tvguideandroid.schedule;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.meetme.android.horizontallistview.HorizontalListView;

import java.util.ArrayList;

/**
 * Created by joaoluis on 02/06/15.
 */
public class SyncedHorizontalScrollListener implements
        HorizontalListView.OnScrollStateChangedListener,
        View.OnTouchListener {

    private static final String TAG = "SyncedHSL";
    private HorizontalListView mMasterHLV;
    private ArrayList<HorizontalListView> mViews;
    private Context mContext;
    private Handler mHandler;
    private int scrollX;


    public SyncedHorizontalScrollListener(Context mContext){
        this.mContext = mContext;
        this.mViews = new ArrayList<>();
        this.scrollX = 0;
        this.mMasterHLV = null;
        this.mHandler = null;
    }

    public void addView(HorizontalListView mView){
        this.mViews.add(mView);
    }

    public int getScrollX() {
        return scrollX;
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        //On Touch Change the HLV Master
        mMasterHLV = (HorizontalListView)v;
        //Execute Touch Listener from Horizontal List View
        return mMasterHLV.getGestureDetector().onTouchEvent(event);
    }

    @Override
    public void onScrollStateChanged(HorizontalListView hlview, ScrollState scrollState) {
        switch (scrollState){
            case SCROLL_STATE_TOUCH_SCROLL:
                if(mMasterHLV != null && hlview.getTag() == mMasterHLV.getTag()){
                    if(mHandler != null) mHandler.removeCallbacks(checkScroll);
                    mHandler = new Handler();
                    mHandler.post(checkScroll);
                }
                break;
            case SCROLL_STATE_FLING:
                break;
            case SCROLL_STATE_IDLE:
                if(mMasterHLV != null && hlview.getTag() == mMasterHLV.getTag()){
                    if (mHandler != null) mHandler.removeCallbacks(checkScroll);
                }
                break;
        }
    }

    /**
     * Scroll the other Horizontal List Views at the Same as their master (The one the User Touches).
     */
    private final Runnable checkScroll = new Runnable() {
        @Override
        public void run() {
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mMasterHLV != null) {
                        scrollX = mMasterHLV.getCurrentScrollX();
                        for (HorizontalListView hlv : mViews)
                            if (mMasterHLV.getTag() != hlv.getTag())
                                hlv.scrollTo(scrollX);
                    }
                }
            });
            mHandler.postDelayed(this, 5);
        }
    };


}