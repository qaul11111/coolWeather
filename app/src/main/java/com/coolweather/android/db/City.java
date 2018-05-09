package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

public class City extends DataSupport {

    private int id;             // 城市 id
    private int provinceId;     // 对应省份 id
    private String cityName;    // 城市名称
    private int cityCode;       // 城市编码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}
