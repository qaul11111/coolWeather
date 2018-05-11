package com.coolweather.android;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.gson.Forecast;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private ImageView backgroundImage; // 背景图片
    private TextView titleCity; // 标题城市名
    private TextView titleUpdateTime; // 标题更新时间
    private TextView nowDegree; // 现在的温度
    private TextView nowWeatherInfo; // 现在的天气状况
    private LinearLayout forecastLayout; // 预报信息
    private TextView aqiText; // aqi 指数
    private TextView pm25Text; // pm2.5 指数
    private TextView comfortableText; // 舒适信息
    private TextView carWashText; // 洗车信息
    private TextView sportText; // 运动信息


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        weatherLayout = findViewById(R.id.weather_layout);
        backgroundImage = findViewById(R.id.background_image);
        titleCity = findViewById(R.id.city_name);
        titleUpdateTime = findViewById(R.id.update_time);
        nowDegree = findViewById(R.id.degree_text);
        nowWeatherInfo = findViewById(R.id.weather_info_text);
        forecastLayout = findViewById(R.id.forecast_layout);
        aqiText = findViewById(R.id.aqi_text);
        pm25Text = findViewById(R.id.pm25_text);
        comfortableText = findViewById(R.id.comfort_text);
        carWashText = findViewById(R.id.carwash_text);
        sportText = findViewById(R.id.sport_text);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherStr = preferences.getString("weather", null);
        if (weatherStr == null) {
            // 没有缓存信息, 从服务器请求数据
            String weatherId = getIntent().getStringExtra("weatherId");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeatherInfo(weatherId);
        } else {
            // 有缓存信息, 则直接解析天气信息
            Weather weather = Utility.handleWeatherResponse(weatherStr);
            if (weather != null) {
                showWeatherInfo(weather);
            } else {
                Toast.makeText(this, "读取天气数据出错", Toast.LENGTH_SHORT).show();
            }
        }

        String pic = preferences.getString("bing_pic", null);
        if (pic == null) {
            loadBingPic();
        } else {
            Glide.with(this).load(pic).into(backgroundImage);
        }
    }

    /**
     * 显示天气信息
     *
     * @param weather
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.getBasic().getCityName();
        String updateTime = weather.getBasic().getUpdate().getUpdateTime().split(" ")[1];
        String degree = weather.getNow().getTemperature() + "°C";
        String weatherInfo = weather.getNow().getMore().getInfo();
        String aqi = weather.getAqi().getCity().getAqi();
        String pm25 = weather.getAqi().getCity().getPm25();
        String comfortable = weather.getSuggestion().getComfortable().getInfo();
        String carWash = weather.getSuggestion().getCarWash().getInfo();
        String sport = weather.getSuggestion().getSport().getInfo();

        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        nowDegree.setText(degree);
        nowWeatherInfo.setText(weatherInfo);

        // 移除之前的天气预报
        forecastLayout.removeAllViews();
        // 动态加载天气预报
        for (Forecast forecast : weather.getForecastList()) {
            View view = getLayoutInflater().inflate(R.layout.forecast_item, forecastLayout, false);
            TextView forecastDateText = view.findViewById(R.id.date_text);
            TextView forecastInfoText = view.findViewById(R.id.info_text);
            TextView forecastMaxText = view.findViewById(R.id.max_text);
            TextView forecastMinText = view.findViewById(R.id.min_text);

            forecastDateText.setText(forecast.getDate());
            forecastInfoText.setText(forecast.getMore().getInfo());
            forecastMaxText.setText(forecast.getTemperature().getMax());
            forecastMinText.setText(forecast.getTemperature().getMin());
            forecastLayout.addView(view);
        }

        aqiText.setText(aqi);
        pm25Text.setText(pm25);
        comfortableText.setText(comfortable);
        carWashText.setText(carWash);
        sportText.setText(sport);
        // 加载完成数据, 显示界面
        weatherLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 从服务器请求天气信息
     *
     * @param weatherId
     */
    private void requestWeatherInfo(String weatherId) {
        String address = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=27e3cf8e77b041e3b24bc56c6b254d6a";
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(WeatherActivity.this, "网络错误, 请稍后重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String weatherStr = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(weatherStr);
                // 切回主线程进行 ui 更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.getStatus())) {
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this)
                                    .edit();
                            editor.putString("weather", weatherStr);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        // 加载背景图
        loadBingPic();
    }

    /**
     * 加载 bing 每日一图
     */
    private void loadBingPic() {
        String address = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(WeatherActivity.this, "加载背景图失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String pic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(WeatherActivity.this)
                        .edit();
                editor.putString("bing_pic", pic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(pic).into(backgroundImage);
                    }
                });
            }
        });
    }
}
