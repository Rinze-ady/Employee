package com.project.util;

import java.time.LocalDate;

/**
 * 类型转换工具类
 */
public class TypeChange {


    /**
     * 将字符串转化为日期类型
     * @param txt 字符串
     * @return 日期对象，如果字符串不满足格式，则返回null
     */
    public static LocalDate getDate(String txt){

        if (txt!= null && txt.matches("\\d{4}-\\d{2}-\\d{2}")){
            return LocalDate.parse(txt);
        }
        return null;
    }
}
