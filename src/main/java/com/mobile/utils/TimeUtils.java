package com.mobile.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static Date getDateForString(String time) {
        if (StringUtils.isEmpty(time)) {
            return new Date();
        }
        Date date = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            date = sdf.parse(time);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }
}
