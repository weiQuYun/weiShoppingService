package com.wqy.wx.back.common.util;


import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.configer.exception.BizException;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author
 */
public class DateUtil {
    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将时间戳转换为时间
     *
     * @param stampString 时间戳
     * @return
     */
    public static String stampToDate(String stampString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long stampLong = new Long(stampString);
        Date date = new Date(stampLong);
        return simpleDateFormat.format(date);
    }

    /**
     * 将Date转换为String yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 将Date转换为String yyyy-MM-dd
     *
     * @param date 时间
     * @return
     */
    public static String date2String(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }


    /**
     * 将时间转换为时间戳
     *
     * @param time 时间
     * @return
     */
    public static String dateToStamp(String time) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        return String.valueOf(date.getTime());
    }

    /**
     * 将格式为 yyyy-MM-dd 转为Date 对象
     *
     * @param date
     * @return
     */
    public static Date newDate(String date) throws Throwable {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        try {
            newDate = dateFormat1.parse(date);
        } catch (ParseException e) {
            throw new BizException("A050");
        }
        return newDate;
    }

    /**
     * 将格式为 yyyy-MM-dd转为Date 对象 默认 1900-01-01 00:00:00
     *
     * @param date
     * @return
     */
    public static Date newDateDefault(String date) throws Throwable {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        try {
            if (StringUtils.isBlank(date)) {
                newDate = dateFormat1.parse(Constant.DEFAULT_DATE);
            } else {
                newDate = dateFormat1.parse(date);
            }
            return newDate;
        } catch (ParseException e) {
            throw new BizException("A050");
        }
    }

    /**
     * Thu Dec 19 08:26:40 CST 2019 转Date
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date zoneToLocalTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = (Date) sdf.parse(dateString);
        return date;
    }

    /**
     * 将格式为 yyyy-MM-dd HH:mm:ss 转为Date 对象
     *
     * @param date
     * @return
     */
    public static Date newDateTime(String date) throws Throwable {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = new Date();
        try {
            newDate = dateFormat1.parse(date);
        } catch (ParseException e) {
            throw new BizException("A050");
        }
        return newDate;
    }


    /**
     * 获取8位时间序号
     *
     * @Description
     * @author butterfly
     */
    public static String getIdPrefix() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        // 今天是第多少天
        int day = c.get(Calendar.DAY_OF_YEAR);
        // 0补位操作 必须满足4位
        String dayFmt = String.format("%1$04d", year);
        // 0补位操作 必须满足4位
        String hourFmt = String.format("%1$04d", day);
        StringBuffer prefix = new StringBuffer();
        prefix.append(dayFmt).append(hourFmt);
        return prefix.toString();
    }

    /**
     * Date转LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
