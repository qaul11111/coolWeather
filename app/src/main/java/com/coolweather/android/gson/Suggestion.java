package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

    @SerializedName("comf")
    private Comfortable comfortable; // 舒适描述
    @SerializedName("cw")
    private CarWash carWash;         // 洗车描述
    private Sport sport;             // 运动描述

    public Comfortable getComfortable() {
        return comfortable;
    }

    public void setComfortable(Comfortable comfortable) {
        this.comfortable = comfortable;
    }

    public CarWash getCarWash() {
        return carWash;
    }

    public void setCarWash(CarWash carWash) {
        this.carWash = carWash;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public class Comfortable {
        @SerializedName("txt")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public class CarWash {
        @SerializedName("txt")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public class Sport {
        @SerializedName("txt")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
