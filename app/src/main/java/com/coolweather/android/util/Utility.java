package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/8.
 */

public class Utility {

    /*
    * 解析和处理服务器返回的省级数据
    * */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                // 获取整个省份的数据
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    // 获取每个具体的省份
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    Log.e("id","111111111111");
                    // 将数据存储到数据库中
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * @param response 请求的数据
     * @param provinceId 省份id
     * @return 是否解析成功
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
           try {
               JSONArray allCities = new JSONArray(response);
               for(int i=0;i<allCities.length();i++){
                   JSONObject cityObject = allCities.getJSONObject(i);
                   City city = new City();
                   city.setCityName(cityObject.getString("name"));
                   city.setCityCode(cityObject.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();
               }
               return true;
           }catch (JSONException e){
               e.printStackTrace();
           }
        }
        return false;
    }

    /*
    * 解析和处理服务器返回的县级数据
    * */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String weatherString) {

        return null;
    }
}
