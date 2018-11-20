package cn.com.startai.socket.global.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * author: Guoqiang_Sun
 * date: 2018/11/16 0016
 * Desc:
 */
public class DateUtils {

    /**
     * 一秒钟
     */
    public static final long ONE_SECOND = 1000;

    /**
     * 一分钟
     */
    public static final long ONE_MINUTE = ONE_SECOND * 60;

    /**
     * 一小时
     */
    public static final long ONE_HOUR = ONE_MINUTE * 60;

    /**
     * 一天
     */
    public static final long ONE_DAY = ONE_HOUR * 24;


    /**
     * @param timestamp 时间戳
     * @return 精确到秒钟的时间戳
     * @throws ParseException
     */
    public static long formatTsToSecondTs(long timestamp) throws ParseException {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date startData = new Date(timestamp);
        String format = mFormat.format(startData);
        Date parse = mFormat.parse(format);
        return parse.getTime();
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到秒钟的时间戳
     */
    public static long fastFormatTsToSecondTs(long timestamp) {
        return timestamp / ONE_SECOND * ONE_SECOND;
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到分钟的时间戳
     * @throws ParseException
     */
    public static long formatTsToMinuteTs(long timestamp) throws ParseException {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        Date startData = new Date(timestamp);
        String format = mFormat.format(startData);
        Date parse = mFormat.parse(format);
        return parse.getTime();
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到分钟的时间戳
     */
    public static long fastFormatTsToMinuteTs(long timestamp) {
        return timestamp / ONE_MINUTE * ONE_MINUTE;
    }


    /**
     * @param timestamp 时间戳
     * @return 精确到小时的时间戳
     * @throws ParseException
     */
    public static long formatTsToHourTs(long timestamp) throws ParseException {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH", Locale.getDefault());
        Date startData = new Date(timestamp);
        String format = mFormat.format(startData);
        Date parse = mFormat.parse(format);
        return parse.getTime();
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到小时的时间戳
     */
    public static long fastFormatTsToHourTs(long timestamp) {
        return timestamp / ONE_HOUR * ONE_HOUR;
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到天的时间戳
     * @throws ParseException
     */
    public static long formatTsToDayTs(long timestamp) throws ParseException {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Date startData = new Date(timestamp);
        String format = mFormat.format(startData);
        Date parse = mFormat.parse(format);
        return parse.getTime();
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到天的时间戳
     */
    public static long fastFormatTsToDayTs(long timestamp) {
        int timezoneOffset = Calendar.getInstance().get(Calendar.ZONE_OFFSET);
        return timestamp / ONE_DAY * ONE_DAY - timezoneOffset;
    }

    /**
     * @param timestamp 时间戳
     * @return 精确到天的时间戳
     */
    public static long fastFormatTsToDayTs(long timestamp, int timezoneDiff) {
        return timestamp / ONE_DAY * ONE_DAY - timezoneDiff;
    }

    /**
     * 根据偏移的天数算出去精确天数的时间戳
     *
     * @param offDay
     * @return
     */
    public static long fastFormatTsToDayOfOffset(int offDay) {
        int timezoneOffset = Calendar.getInstance().get(Calendar.ZONE_OFFSET);
        return fastFormatTsToDayOfOffset(System.currentTimeMillis(), offDay, timezoneOffset);
    }

    /**
     * 根据偏移的天数算出去精确天数的时间戳
     *
     * @param offDay
     * @return
     */
    public static long fastFormatTsToDayOfOffset(int offDay, int timezoneDiff) {
        return fastFormatTsToDayOfOffset(System.currentTimeMillis(), offDay, timezoneDiff);
    }

    /**
     * 根据偏移的天数算出去精确天数的时间戳
     *
     * @param startMillis
     * @param offDay
     * @return
     */
    public static long fastFormatTsToDayOfOffset(long startMillis, int offDay, int timezoneDiff) {
        long remain = startMillis % ONE_DAY;
        if (offDay < 0) {
            offDay = 0;
        }
        return startMillis - remain - ONE_DAY * offDay - timezoneDiff;
    }


    /**
     * 获取一天有多少分钟
     *
     * @return 分钟数据
     */
    public static int getMinuteOfDay(long timestamp, int i) {
        Date date = new Date(timestamp);
        int hours = date.getHours();
        int minutes = date.getMinutes();
        if (i > 0) {
            int d = minutes % i;
            minutes -= d;
            if (minutes < 0) {
                minutes = 0;
            }
        }
        return hours * 60 + minutes;
    }

    /**
     * 获取一天有多少分钟
     *
     * @return 分钟数据
     */
    public static int getMinuteOfDay(long timestamp) {
        Date date = new Date(timestamp);
        int hours = date.getHours();
        int minutes = date.getMinutes();
        return hours * 60 + minutes;
    }

    /**
     * 一个月有多少天
     *
     * @param mDate
     * @return
     */
    public static int getDaysOfMonth(Date mDate) {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.YEAR, mDate.getYear());
        cl.set(Calendar.MONTH, mDate.getMonth());
        return cl.getActualMaximum(Calendar.DATE);
    }

    /**
     * 一个月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.YEAR, year);
        cl.set(Calendar.MONTH, month);
        return cl.getActualMaximum(Calendar.DATE);
    }

    public static int getCurYear() {
        return getYear(System.currentTimeMillis());
    }

    public static int getYear(long millis) {
        return getYear(new Date(millis));
    }

    public static int getYear(Date mDate) {
        return mDate.getYear() + 1900;
    }

    public static int getCurMonth() {
        return getMonth(System.currentTimeMillis());
    }

    public static int getMonth(long millis) {
        return getMonth(new Date(millis));
    }

    public static int getMonth(Date mDate) {
        return mDate.getMonth() + 1;
    }

    public static int getCurDays() {
        return getDays(System.currentTimeMillis());
    }

    public static int getDays(long millis) {
        return getDays(new Date(millis));
    }

    public static int getDays(Date mDate) {
        return mDate.getDate();
    }

    public static int getCurWeek() {
        return getWeek(System.currentTimeMillis());
    }

    public static int getWeek(long millis) {
        return getWeek(new Date(millis));
    }

    public static int getWeek(Date mDate) {
        return mDate.getDay();
    }


    public static void main(String[] args) {
        System.out.println(" DateUtils main ");

        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault());

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(" curTime: " + currentTimeMillis + " " + mFormat.format(new Date(currentTimeMillis)));

        long l;


        try {
            l = formatTsToSecondTs(currentTimeMillis);
            System.out.println(" formatTsToSecondTs: " + l + " " + mFormat.format(new Date(l)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        l = fastFormatTsToSecondTs(currentTimeMillis);
        System.out.println(" formatTsToSecondTs: " + l + " " + mFormat.format(new Date(l)));


        try {
            l = formatTsToMinuteTs(currentTimeMillis);
            System.out.println(" formatTsToMinuteTs: " + l + " " + mFormat.format(new Date(l)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        l = fastFormatTsToMinuteTs(currentTimeMillis);
        System.out.println(" fastFormatTsToMinuteTs: " + l + " " + mFormat.format(new Date(l)));

        try {
            l = formatTsToHourTs(currentTimeMillis);
            System.out.println(" formatTsToHourTs: " + l + " " + mFormat.format(new Date(l)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        l = fastFormatTsToHourTs(currentTimeMillis);
        System.out.println(" fastFormatTsToHourTs: " + l + " " + mFormat.format(new Date(l)));


        try {
            l = formatTsToDayTs(currentTimeMillis);
            System.out.println(" formatTsToDayTs: " + l + " " + mFormat.format(new Date(l)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int i = Calendar.getInstance().get(Calendar.ZONE_OFFSET);

        l = fastFormatTsToDayTs(currentTimeMillis, i);
        System.out.println(" fastFormatTsToDayTs: " + l + " " + mFormat.format(new Date(l)));

        l = fastFormatTsToDayOfOffset(currentTimeMillis, 1, i);

        System.out.println(" fastFormatTsToDayOfOffset: " + l + " " + mFormat.format(new Date(l)));


        long minuteOfDay = getMinuteOfDay(currentTimeMillis, 7);
        System.out.println(" getMinuteOfDay: " + minuteOfDay);

        int daysOfMonth = getDaysOfMonth(new Date(System.currentTimeMillis()));
        System.out.println(" daysOfMonth: " + daysOfMonth);

        int month1 = new Date(System.currentTimeMillis()).getMonth();
        System.out.println(" month1: " + month1);

        for (int j = 0; j < 12; j++) {
            int month = j + 1;
            daysOfMonth = getDaysOfMonth(2018, month);
            System.out.println(" daysOfMonth: " + month + " " + daysOfMonth);
        }

        int curYear = getCurYear();
        System.out.println(" curYear: " + curYear);

        int curDays = getCurDays();
        System.out.println(" curDays: " + curDays);


        long diffMonth = fastFormatTsToDayTs(currentTimeMillis - DateUtils.ONE_DAY * 31 * 7);
        System.out.println(" diffMonth: " + diffMonth + " " + mFormat.format(new Date(diffMonth)));


        for (int d = 0; d < 10; d++) {
            Calendar instance = Calendar.getInstance();
            long l1 = System.currentTimeMillis() - ONE_DAY * d;
            instance.setTimeInMillis(l1);
            Date date = instance.getTime();
            byte year = (byte) (date.getYear() + 1900 - 2000);
            byte month = (byte) (date.getMonth() + 1);
            byte day = (byte) date.getDate();
            byte hours = (byte) date.getHours();
            byte minutes = (byte) date.getMinutes();
            byte seconds = (byte) date.getSeconds();
            byte week = (byte) date.getDay();
            System.out.println(d + " - " + year + " : " + month + " : " + day + " : " + hours
                    + " : " + minutes + " : " + seconds + " : " + week);


            System.out.println(getWeek(l1));
        }


    }

}