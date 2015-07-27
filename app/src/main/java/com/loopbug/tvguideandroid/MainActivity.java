package com.loopbug.tvguideandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopbug.tvguideandroid.schedule.AdapterHorizontalHeader;
import com.loopbug.tvguideandroid.schedule.AdapterVerticalSchedule;
import com.meetme.android.horizontallistview.HorizontalListView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {

    private static final String TAG = "MAIN_ACTIVITY";
    private final int durationA = 1800;
    private final int durationB = 2700;
    private final int durationC = 3600;
    private final int durationD = 1200;
    public static final String date = "2015-07-23";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a Objects Array with the Channels and theirs a Schedule (make sure that every Channel as a complete schedule without gaps).
        //Create Dummy Objects if you need
        final ArrayList<Channel> mChannels = this.createSampleSchedule();

        //Headed for the Hours
        final HorizontalListView listHeader = (HorizontalListView)findViewById(R.id.hlv_header);
        //Column for the Channels and theirs schedules
        final ListView listColumn = (ListView)findViewById(R.id.list_column_tv_schedule);

        //Adapter with the hours
        final AdapterHorizontalHeader mAdapterHorizontalHeader =
                new AdapterHorizontalHeader(this, R.layout.list_item_hours,
                        new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.label_hours))));
        listHeader.setAdapter(mAdapterHorizontalHeader);

        //Adapter with the Channels and theis Schedules Adapters
        final AdapterVerticalSchedule mAdapterVerticalFullSchedule =
                new AdapterVerticalSchedule(this, R.layout.list_row_channel_schedule, mChannels);
        listColumn.setAdapter(mAdapterVerticalFullSchedule);

        //Sync scrolls
        listHeader.setTag(-1);
        mAdapterVerticalFullSchedule.addHorizontalScrollView(listHeader);
        listHeader.setOnScrollStateChangedListener(mAdapterVerticalFullSchedule.getSyncedHorizontalScrollListener());
        listHeader.setOnTouchListener(mAdapterVerticalFullSchedule.getSyncedHorizontalScrollListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Channel> createSampleSchedule(){
        final ArrayList<Channel> mChannels = new ArrayList<>();
        final ArrayList<Schedule> mScheduleA = new ArrayList<>();
        final ArrayList<Schedule> mScheduleB = new ArrayList<>();

        try{
            mScheduleA.add(new Schedule(0, "Program A ep3", durationA,
                    ManagerTime.getMilliForDate("06:00:00", date), ManagerTime.getMilliForDate("06:00:00", date, durationA), false));
            mScheduleA.add(new Schedule(1, "Program A ep4", durationC,
                    ManagerTime.getMilliForDate("06:30:00", date), ManagerTime.getMilliForDate("06:30:00", date, durationC), false));
            mScheduleA.add(new Schedule(2, "Program A ep5", durationA,
                    ManagerTime.getMilliForDate("07:30:00", date), ManagerTime.getMilliForDate("07:30:00", date, durationA), false));
            mScheduleA.add(new Schedule(3, "Program A ep6", durationC,
                    ManagerTime.getMilliForDate("08:00:00", date), ManagerTime.getMilliForDate("08:00:00", date, durationC), false));
            mScheduleA.add(new Schedule(4, "Program B ep9", durationA,
                    ManagerTime.getMilliForDate("09:00:00", date), ManagerTime.getMilliForDate("09:00:00", date, durationA), false));
            mScheduleA.add(new Schedule(5, "Program B ep10", durationB,
                    ManagerTime.getMilliForDate("10:00:00", date), ManagerTime.getMilliForDate("10:00:00", date, durationB), false));
            mScheduleA.add(new Schedule(6, "Program B ep11", durationC,
                    ManagerTime.getMilliForDate("11:30:00", date), ManagerTime.getMilliForDate("11:30:00", date, durationC), false));
            mScheduleA.add(new Schedule(7, "Program B ep12", durationD,
                    ManagerTime.getMilliForDate("12:30:00", date), ManagerTime.getMilliForDate("12:30:00", date, durationD), false));
            mScheduleA.add(new Schedule(8, "Program C ep1", durationC,
                    ManagerTime.getMilliForDate("14:00:00", date), ManagerTime.getMilliForDate("14:00:00", date, durationC), false));
            mScheduleA.add(new Schedule(9, "Program C ep2", durationA,
                    ManagerTime.getMilliForDate("15:00:00", date), ManagerTime.getMilliForDate("15:00:00", date, durationA), false));
            mScheduleA.add(new Schedule(10, "Program C ep3", durationB,
                    ManagerTime.getMilliForDate("16:00:00", date), ManagerTime.getMilliForDate("16:00:00", date, durationB), false));
            mScheduleA.add(new Schedule(11, "Program C ep4", durationB,
                    ManagerTime.getMilliForDate("17:00:00", date), ManagerTime.getMilliForDate("17:00:00", date, durationB), false));
            mScheduleA.add(new Schedule(12, "Program C ep5", durationD,
                    ManagerTime.getMilliForDate("18:00:00", date), ManagerTime.getMilliForDate("18:00:00", date, durationD), false));

            this.makeSchedule24Hours(mScheduleA);

            mScheduleB.add(new Schedule(0, "Program A ep3", durationA,
                    ManagerTime.getMilliForDate("03:00:00", date), ManagerTime.getMilliForDate("03:00:00", date, durationA), false));
            mScheduleB.add(new Schedule(1, "Program A ep4", durationC,
                    ManagerTime.getMilliForDate("03:30:00", date), ManagerTime.getMilliForDate("03:30:00", date, durationC), false));
            mScheduleB.add(new Schedule(2, "Program A ep5", durationA,
                    ManagerTime.getMilliForDate("04:30:00", date), ManagerTime.getMilliForDate("04:30:00", date, durationA), false));
            mScheduleB.add(new Schedule(3, "Program A ep6", durationC,
                    ManagerTime.getMilliForDate("05:00:00", date), ManagerTime.getMilliForDate("05:00:00", date, durationC), false));
            mScheduleB.add(new Schedule(4, "Program B ep9", durationA,
                    ManagerTime.getMilliForDate("06:00:00", date), ManagerTime.getMilliForDate("06:00:00", date, durationA), false));
            mScheduleB.add(new Schedule(5, "Program B ep10", durationB,
                    ManagerTime.getMilliForDate("07:00:00", date), ManagerTime.getMilliForDate("07:00:00", date, durationB), false));
            mScheduleB.add(new Schedule(6, "Program B ep11", durationC,
                    ManagerTime.getMilliForDate("08:30:00", date), ManagerTime.getMilliForDate("08:30:00", date, durationC), false));
            mScheduleB.add(new Schedule(7, "Program B ep12", durationD,
                    ManagerTime.getMilliForDate("09:30:00", date), ManagerTime.getMilliForDate("09:30:00", date, durationD), false));
            mScheduleB.add(new Schedule(8, "Program C ep1", durationC,
                    ManagerTime.getMilliForDate("10:00:00", date), ManagerTime.getMilliForDate("10:00:00", date, durationC), false));
            mScheduleB.add(new Schedule(9, "Program C ep2", durationA,
                    ManagerTime.getMilliForDate("11:00:00", date), ManagerTime.getMilliForDate("11:00:00", date, durationA), false));
            mScheduleB.add(new Schedule(10, "Program C ep3", durationB,
                    ManagerTime.getMilliForDate("12:00:00", date), ManagerTime.getMilliForDate("12:00:00", date, durationB), false));
            mScheduleB.add(new Schedule(11, "Program C ep4", durationB,
                    ManagerTime.getMilliForDate("13:00:00", date), ManagerTime.getMilliForDate("13:00:00", date, durationB), false));
            mScheduleB.add(new Schedule(12, "Program C ep5", durationD,
                    ManagerTime.getMilliForDate("15:00:00", date), ManagerTime.getMilliForDate("15:00:00", date, durationD), false));

            this.makeSchedule24Hours(mScheduleB);

        }catch (Exception e){
            Log.e(TAG, "Error", e);
        }
        Log.d(TAG, "Schedule Size A:" + mScheduleA.size());
        Log.d(TAG, "Schedule Size B:" + mScheduleB.size());

        mChannels.add(new Channel(0, "SIC", mScheduleA));
        mChannels.add(new Channel(1, "TVI", mScheduleB));
        mChannels.add(new Channel(2, "RTP", mScheduleA));
        mChannels.add(new Channel(3, "SPORT TV", mScheduleB));
        mChannels.add(new Channel(4, "PANDA", mScheduleB));
        mChannels.add(new Channel(5, "FOX", mScheduleA));
        mChannels.add(new Channel(6, "AXN", mScheduleB));
        mChannels.add(new Channel(7, "SYFY", mScheduleA));
        mChannels.add(new Channel(8, "CBN", mScheduleB));
        mChannels.add(new Channel(9, "BBC", mScheduleA));
        mChannels.add(new Channel(10, "AXN WHITE", mScheduleB));
        mChannels.add(new Channel(11, "AXN BLACK", mScheduleB));
        mChannels.add(new Channel(12, "FOX CRIME", mScheduleA));
        mChannels.add(new Channel(13, "FOX LIFE", mScheduleB));
        mChannels.add(new Channel(14, "HISTORIA", mScheduleA));
        mChannels.add(new Channel(15, "ODISSEIA", mScheduleA));

        return mChannels;
    }

    /**
     * Fill the gaps in the Channel Schedule if exists
     * @param mSchedule
     * @throws Exception
     */
    private void makeSchedule24Hours(ArrayList<Schedule> mSchedule) throws Exception {
        final ArrayList<Schedule> tmp = new ArrayList<>();
        tmp.addAll(mSchedule);

        mSchedule.clear();
        if(tmp.size() > 0) {
            long preMillis = ManagerTime.getMilliForDate("00:00:00", date);
            for(int i=0; i<tmp.size(); i++){
                final Schedule item = tmp.get(i);
                final Schedule prevItem = (i>0) ? tmp.get(i-1) : null;

                if(preMillis != item.getStartHour()) {
                    final long startHour = (prevItem == null) ? preMillis : prevItem.getEndHour();
                    final int duration = (int)(item.getStartHour() - startHour) / 1000;
                    mSchedule.add(new Schedule(-1, "", duration, startHour, item.getStartHour(), true));
                    mSchedule.add(item);
                }else{
                    mSchedule.add(item);
                }
                preMillis = item.getEndHour();
            }

            //Check for last Element
            final long lastMillisOfDay = ManagerTime.getLastMilliOfDay(date);
            final Schedule lastItem = mSchedule.get(mSchedule.size()-1);
            if(lastItem.getEndHour() <= lastMillisOfDay) {
                final long startHour = lastItem.getStartHour();
                final int duration = (int)(lastMillisOfDay - lastItem.getEndHour()) / 1000;
                mSchedule.add(new Schedule(-1, "", duration, startHour, lastMillisOfDay, true));
            }
        }else{
            final long lastMillisOfDay = ManagerTime.getLastMilliOfDay(date);
            final long firstMillisOfDay = ManagerTime.getFirstMilliOfDay(date);
            mSchedule.add(new Schedule(-1, "", (int)(lastMillisOfDay-firstMillisOfDay) / 1000, firstMillisOfDay, lastMillisOfDay, true));
        }
    }
}
