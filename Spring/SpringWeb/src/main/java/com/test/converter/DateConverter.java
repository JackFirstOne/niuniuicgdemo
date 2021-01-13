package com.test.converter;



import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期类型转换器
 */
public class DateConverter implements Converter<String, Date> {


    public Date convert(String s) {
        //将日期字符串转换成日期对象并返回
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sd.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
