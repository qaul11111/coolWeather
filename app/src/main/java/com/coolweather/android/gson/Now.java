package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Now {

    @SerializedName("tmp")
    private String temperature; // 温度
    @SerializedName("cond")
    private More more;          // 更多天气信息

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public More getMore() {
        return more;
    }

    public void setMore(More more) {
        this.more = more;
    }

    public class More {

        @SerializedName("txt")
        private String info;    // 天气信息

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
