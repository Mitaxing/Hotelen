package com.kupa.hotel.utils;


import com.kupa.hotel.entity.Recommend;
import com.kupa.hotel.entity.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/16.
 */

public class JsonParse {

    /**
     * 解析天气信息
     *
     * @param result 返回信息
     * @return List<Weather>
     */
    public static List<Weather> parseWeather(String result) {
        List<Weather> weathers = new ArrayList<>();
        JSONTokener parse = new JSONTokener(result);
        try {
            JSONObject obj = (JSONObject) parse.nextValue();
            if (obj.optInt("error") == 0) {
                JSONArray results = obj.optJSONArray("results");
                int len = results.length();
                for (int i = 0; i < len; i++) {
                    JSONObject weatherObj = results.optJSONObject(i);
                    JSONArray weather_data = weatherObj.optJSONArray("weather_data");
                    int length = weather_data.length();
                    for (int j = 0; j < length; j++) {
                        JSONObject info = weather_data.optJSONObject(j);
                        String date = info.optString("date");
                        String weather = info.optString("weather");
                        String temperature = info.optString("temperature");
                        String nightPictureUrl = info.optString("nightPictureUrl");
                        String dayPictureUrl = info.optString("dayPictureUrl");

                        if (date.length() > 2)
                            date = date.substring(0, 2);
                        Weather w = new Weather(weather, nightPictureUrl, dayPictureUrl, temperature, date);
                        weathers.add(w);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;
    }

    /**
     * 解析首页推荐信息
     *
     * @param result 返回信息
     * @return List<Recommend>
     */
    public static List<Recommend> parseRecommend(String result) {
        List<Recommend> list = new ArrayList<>();
        JSONTokener parse = new JSONTokener(result);
        try {
            JSONObject object = (JSONObject) parse.nextValue();
            if (object.optString("res").equals("ok")) {
                JSONArray cateInfo = object.optJSONArray("info");
                int len = cateInfo.length();
                for (int i = 0; i < len; i++) {
                    JSONObject cate = (JSONObject) cateInfo.get(i);
                    int type = cate.optInt("type");
                    Recommend recommend = new Recommend(cate.optInt("id"), type, resolveTitle(type),
                            cate.optString("name"), cate.optString("description"), cate.optString("imagePath"));
                    list.add(recommend);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String resolveTitle(int type) {
        String title = "美食";
        switch (type) {
            case Contacts.RECOMMEND_TOUR:
                title = "景点";
                break;

            case Contacts.RECOMMEND_MOVIE:
                title = "电影";
                break;

            case Contacts.RECOMMEND_SHOP:
                title = "商品";
                break;
        }
        return title;
    }
}
