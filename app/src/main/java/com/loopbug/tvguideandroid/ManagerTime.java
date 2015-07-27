package com.loopbug.tvguideandroid;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by joaoluis on 21/07/15.
 */
public class ManagerTime {

    public static long getMilliForDate(String hour, String date) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date mDate = sdf.parse(date+" "+hour);
        Calendar mCal = Calendar.getInstance();
        mCal.setTime(mDate);
        return mCal.getTime().getTime();
    }

    public static long getMilliForDate(String hour, String date, int durationS) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date mDate = sdf.parse(date+" "+hour);
        Calendar mCal = Calendar.getInstance();
        mCal.setTime(mDate);
        mCal.add(Calendar.SECOND, durationS);
        return mCal.getTime().getTime();
    }

    public static long getLastMilliOfDay(String date) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date mDate = sdf.parse(date+" 00:30:00");
        Calendar mCal = Calendar.getInstance();
        mCal.setTime(mDate);
        mCal.add(Calendar.DAY_OF_MONTH, 1);
        return  mCal.getTime().getTime();
    }

    public static long getFirstMilliOfDay(String date) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date mDate = sdf.parse(date+" 00:00:00");
        Calendar mCal = Calendar.getInstance();
        mCal.setTime(mDate);
        return  mCal.getTime().getTime();
    }

    public static String getStringHour(long timeInMillis) {
        Calendar mCal = Calendar.getInstance();
        mCal.setTimeInMillis(timeInMillis);
        return mCal.get(Calendar.HOUR_OF_DAY)+":"+mCal.get(Calendar.MINUTE)+":"+mCal.get(Calendar.SECOND);
    }
}
