package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    private String date;                // 日期
    @SerializedName("cond")
    private More more;                  // 天气信息
    @SerializedName("tmp")
    private Temperature temperature;    // 温度信息

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public More getMore() {
        return more;
    }

    public void setMore(More more) {
        this.more = more;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public class More {
        @SerializedName("txt_d")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public class Temperature {
        private String max; // 最大温度
        private String min; // 最小温度

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }
}
