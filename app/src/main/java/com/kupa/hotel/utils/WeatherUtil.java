package com.kupa.hotel.utils;


import android.text.TextUtils;

import com.kupa.hotel.entity.Weather;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by Mita on 2017/3/16.
 */

public class WeatherUtil {

    private static DbManager db;

    /**
     * 拼接天气信息
     * @param weather 天气信息
     * @return 格式化的天气信息
     */
    public static String montageWeather(Weather weather){
        String weatherTemp = weather.getTemperature();
        String spStr[] = weatherTemp.split(" ~");
        weatherTemp = spStr[1] + " ~ " + spStr[0] + "℃";
        return (weather.getWeather() + " | " + weatherTemp);
    }

    /**
     * 获取天气信息
     * @return Weather
     */
    public static Weather getWeather() {
        String week = DateUtils.getWeek();
        String firstDay = WeatherUtil.getFirstDayWeather();
        if (!TextUtils.isEmpty(firstDay) && !week.equals(firstDay)) {
            WeatherUtil.deleteOneWeather(firstDay);
        }
        return WeatherUtil.getOneWeather(week);
    }

    /**
     * 保存天气到数据库
     *
     * @param weathers 天气list
     */
    public static void saveWeather(List<Weather> weathers) {
        try {
            db = DatabaseUtil.getManager();
            db.saveOrUpdate(weathers);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某一天的天气
     *
     * @param day 周几
     * @return Weather
     */
    public static Weather getOneWeather(String day) {
        Weather weather = null;
        try {
            db = DatabaseUtil.getManager();
            weather = db.findById(Weather.class, day);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return weather;
    }

    /**
     * 获取第一条天气
     *
     * @return 天气
     */
    public static String getFirstDayWeather() {
        String week = "";
        try {
            db = DatabaseUtil.getManager();
            Weather weather = db.findFirst(Weather.class);
            if (null != weather)
                week = weather.getDate();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return week;
    }

    /**
     * 删除某天天气
     *
     * @param day 周几
     */
    public static void deleteOneWeather(String day) {
        try {
            db = DatabaseUtil.getManager();
            db.deleteById(Weather.class, day);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
