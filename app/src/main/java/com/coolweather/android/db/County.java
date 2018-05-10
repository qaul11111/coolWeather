package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

public class County extends DataSupport {

    private int id;             // 县区 id
    private int cityId;         // 对应的城市 id
    private String countyName;  // 县区名称
    private String weatherId;      // 天气 id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
