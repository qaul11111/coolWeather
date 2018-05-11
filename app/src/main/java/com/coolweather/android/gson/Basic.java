package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {

    @SerializedName("city")
    private String cityName;    // 城市名称
    @SerializedName("id")
    private String weatherId;   // 天气 id
    private Update update;      // 更新信息

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    /**
     * 更新时间
     */
    public class Update {

        @SerializedName("loc")
        private String updateTime;  // 更新时间

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
