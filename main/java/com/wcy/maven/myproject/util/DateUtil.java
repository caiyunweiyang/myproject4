package com.wcy.maven.myproject.util;

import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间工具类
 *
 * @author liuzw
 * @date 2020-02-18 13:40
 */
@Slf4j
public class DateUtil {
    /**
     * 标准日期
     */
    public static final String NORM_DATE_FORMAT_STR = "yyyy-MM-dd";
    /**
     * 标准时间
     */
    public static final String NORM_TIME_FORMAT_STR = "HH:mm:ss";
    /**
     * 标准日期时间
     */
    public static final String NORM_DATETIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
    /**
     * 无分隔符日期时间
     */
    public static final String NO_DELIMITER_DATETIME_FORMAT_STR = "yyyyMMddHHmmss";
    /**
     * 年月
     */
    public static final String YEAR_MONTH_FORMAT_STR = "yyyy-MM";

    /**
     * 年月日
     */
    public static final String YEAR_MONTH_DAY_FORMAT_STR = "yyyyMMdd";

    private static final Integer MINUTES = 60000;

    private static final Integer SECONDS = 3600;

    private static final Integer ERRORSECONDS = 60;

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 格式 yyyyMMdd
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateYMD(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT_STR, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static Date parseDateTime(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR);
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + NORM_DATETIME_FORMAT_STR + " error!", e);
        }
        return null;
    }

    /**
     * 比较两个时间的大小,time1 > time2 返回1，time1 < time2返回-1，time1 = time2返回0
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int compareDate(String time1, String time2) {
        Date dt1 = DateUtil.parseDateTime(time1);
        Date dt2 = DateUtil.parseDateTime(time2);
        return Long.compare(dt1.getTime(), dt2.getTime());
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     *
     * @param date          基准日期
     * @param calendarField 偏移的粒度大小（小时、天、月等）使用Calendar中的常数
     * @param offsite       偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date getOffsiteDate(Date date, int calendarField, int offsite) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }

    /**
     * string转date
     *
     * @param date
     * @return
     */
    public static Date getDateByString(String date) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR);
            return ft.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }


    /**
     * date转string
     *
     * @param date
     * @return
     */
    public static String getStringByDate(Date date) {
        Format formatter = new SimpleDateFormat(NORM_DATETIME_FORMAT_STR);
        return formatter.format(date);
    }

    /**
     * 根据特定格式格式化日期
     *
     * @param date   被格式化的日期
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取指定日期那天的开始时间
     *
     * @param date 指定的日期
     * @return 指定日期的开始时间
     */
    public static Date getStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 比较两个时间字符串中小时和分钟是否相同
     *
     * @param str1 时间字符串1
     * @param str2 时间字符串2
     * @return 是否相同
     */
    public static boolean compareHoursAndMinutes(Date str1, Date str2) {
        if (Math.abs(str1.getTime() - str2.getTime()) <= MINUTES) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 将特定格式的日期转换为Date对象
     *
     * @param dateString 特定格式的日期
     * @param format     格式，例如yyyy-MM-dd
     * @return 日期对象
     */
    public static Date parse(String dateString, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + format + " error!", e);
        }
        return null;
    }

    public static Boolean compareHour(Integer transactionExpirationTime, String startTime) {
        Date nowDate = new Date();
        Long seconds = (nowDate.getTime() - getDateByString(startTime).getTime()) / 1000;
        //可以有60S的误差
        if (seconds >= (transactionExpirationTime * SECONDS) - ERRORSECONDS) {
            //可以进行流水对账
            return true;
        } else {
            return false;
        }
    }

    /**
     * 指定时间向后推指定天数之后，计算和当前相差的时间
     *
     * @param time       指定时间
     * @param offsiteDay 偏移的时间
     * @return 几天几小时几分钟
     */
    public static String getRemainingTime(String time, Integer offsiteDay) {
        try {
            // 按照传入的格式生成一个simpledateformate对象
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 获取指定时间向后偏移后的时间
            Date offsiteDate = getOffsiteDate(sd.parse(time), Calendar.DATE, offsiteDay);
            String strOffsiteDate = formatDateTime(offsiteDate);
            //一天的毫秒数
            long nd = 1000 * 24 * 60 * 60;
            // 一小时的毫秒数
            long nh = 1000 * 60 * 60;
            // 一分钟的毫秒数
            long nm = 1000 * 60;
            // 一秒钟的毫秒数
            long ns = 1000;
            // 获得两个时间的毫秒时间差异
            long diff = sd.parse(strOffsiteDate).getTime() - System.currentTimeMillis();
            // 计算差多少天
            long day = diff / nd;
            // 计算差多少小时
            long hour = diff % nd / nh + day * 24;
            // 计算差多少分钟
            long min = diff % nd % nh / nm + day * 24 * 60;
            // 计算差多少秒
            long sec = diff % nd % nh % nm / ns;
            // 输出结果
            StringBuilder result = new StringBuilder();
            result.append(day < 0 ? 0 : day).append("天")
                    .append((hour - day * 24) < 0 ? 0 : (hour - day * 24)).append("小时")
                    .append((min - day * 24 * 60) < 0 ? 0: (min - day * 24 * 60)).append("分钟");
            return result.toString();
        } catch (ParseException e) {
            log.error("ParseException error:{}", e);
            return "";
        }
    }

    public static String  compareDate(Date d1, Date d2) {
        long dif = d1.getTime() - d2.getTime();
        long day= dif /(24*60*60*1000);
        long hour=( dif /(60*60*1000)-day*24);
        long min=(( dif /(60*1000))-day*24*60-hour*60);
        long s =(dif / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String timeDifference = day + "天" + hour + "小时" + min + "分" + s + "秒";
        return timeDifference;

    }
     public static void main(String[] args) {
         Date dateByString = getDateByString("2020-03-25 09:54:24");
         Date dateByString1 = getDateByString("2020-03-25 10:58:48");
         String timeDifference= compareDate(dateByString1, dateByString);
         System.out.println(timeDifference);
     }
    public static String DateChange(String time) {
          Date date = getDateByString(time);
          return format(date,NORM_DATE_FORMAT_STR);
    }
}
