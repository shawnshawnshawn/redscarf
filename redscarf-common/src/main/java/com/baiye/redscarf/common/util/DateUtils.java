package com.baiye.redscarf.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @date 2020/6/2 下午3:28
 */
public class DateUtils {

    final static String FORMAT_YMDHMS_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转成string类型
     * @param date
     * @return
     */
    public static String parseString(Date date) {
        return parseStringByFormat(date, FORMAT_YMDHMS_DEFAULT);
    }

    /**
     * 根据日期类型转成string类型
     */
    public static String parseStringByFormat(Date date, String format) {
        if (date == null || StringUtils.isBlank(format)) {
            throw  new RuntimeException("日期转化参数不能为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * string转成日期格式
     */
    public static Date parseDate(String dateStr) {
        return parseDateByFormat(dateStr, FORMAT_YMDHMS_DEFAULT);
    }

    /**
     * 根据日期类型string转成日期格式
     */
    public static Date parseDateByFormat(String dateStr, String format) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(format)) {
            throw new RuntimeException("日期转化参数不能为空");
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某一天的开始时间
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            throw new RuntimeException("获取日期开始时间参数不能为空");
        }
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    /**
     * 获取某一天的开始时间
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            throw new RuntimeException("获取日期开始时间参数不能为空");
        }
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }
}
