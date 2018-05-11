package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    private String status;                  // 状态
    private Basic basic;                    // 基本信息
    private Now now;                        // 当前信息
    private AQI aqi;                        // 城市 AQI 信息
    private Suggestion suggestion;          // 建议信息
    @SerializedName("daily_forecast")
    private List<Forecast> forecastList;    // 天气预报

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public AQI getAqi() {
        return aqi;
    }

    public void setAqi(AQI aqi) {
        this.aqi = aqi;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}
