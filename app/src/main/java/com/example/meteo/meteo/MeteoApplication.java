package com.example.meteo.meteo;

import android.app.Application;

/**
 * Created by Gogo on 11/12/2017.
 */

public class MeteoApplication extends Application {

    public static final String OPENWEATHER_API_KEY = "0a0118c90a7f35c28af556e858c8714e";

    public final static String OPENWEATHER_URL_LOCAL_WEATHER = "http://api.openweathermap.org/data/2.5/forecast?";

    public static String getOpenweatherApiKey() {
        return OPENWEATHER_API_KEY;
    }

    public static String getOpenweatherUrlLocalWeather() {
        return OPENWEATHER_URL_LOCAL_WEATHER;
    }
}
