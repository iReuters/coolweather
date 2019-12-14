package com.example.firstandroidcode.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.firstandroidcode.MyToast;
import com.example.firstandroidcode.R;
import com.example.firstandroidcode.coolweather.gson.Weather;

public class CoolWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool_weather);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        /*if (preferences.getString("weather", null) != null) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d(MyToast.mTAG, "onCreate: " + "weather = null");;

        }*/
    }
}
