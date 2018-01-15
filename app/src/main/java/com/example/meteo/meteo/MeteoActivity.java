package com.example.meteo.meteo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gogo on 11/12/2017.
 */

public class MeteoActivity extends Activity {

    ProgressDialog pd;
    MeteoApplication myApp;
    String response;
    String ville;
    private TextView cityNameView, todayTemp, todayWeather,
            day1Name, day1Temp, day1Weather,
            day2Name, day2Temp, day2Weather,
            day3Name, day3Temp, day3Weather,
            day4Name, day4Temp, day4Weather;

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(MeteoActivity.this, MainActivity.class);
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meteo_layout);
        TextView city_name = (TextView) findViewById(R.id.city_display);
        myApp = (MeteoApplication) this.getApplication();
        String city = String.valueOf(getIntent().getExtras().get("city"));
        ville = city;
        city_name.setText(city);

        new JsonTask().execute(myApp.getOpenweatherUrlLocalWeather()+
                "q="+city+"&appid="+myApp.getOpenweatherApiKey());
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MeteoActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }
                response = buffer.toString();
                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }

            JSONObject jsonResponse = null;
            try {
                if(response != null) {
                    jsonResponse = new JSONObject(response);
                    JSONArray list = jsonResponse.getJSONArray("list");
                    JSONObject resultat = list.getJSONObject(0);
                    double temp = resultat.getJSONObject("main").getDouble("temp");
                    String temperature;
                    temp = temp - 273.15;
                    temperature = String.valueOf(temp).substring(0, 3) + " °C";
                    TextView temp_jour = (TextView) findViewById(R.id.today_temperature);
                    temp_jour.setText(temperature);

                    String weather = resultat.getJSONArray("weather").getJSONObject(0).getString("main");
                    TextView weather_jour = (TextView) findViewById(R.id.today_weather);
                    weather_jour.setText(weather);

                    ImageView imgView = (ImageView) findViewById(R.id.today_weather_image);


                    switch (weather) {
                        case "Clear":
                            imgView.setImageResource(R.drawable.clear);
                            break;
                        case "Clouds":
                            imgView.setImageResource(R.drawable.clouds);
                            break;
                        case "Rain":
                            imgView.setImageResource(R.drawable.rain);
                            break;
                        case "Snow":
                            imgView.setImageResource(R.drawable.snow);
                            break;
                        default:
                            imgView.setImageResource(R.drawable.snow);
                    }

                    resultat = list.getJSONObject(8);
                    temp = resultat.getJSONObject("main").getDouble("temp");
                    temp -= 273.15;
                    temperature = String.valueOf(temp).substring(0, 3) + " °C";
                    TextView temp_jourp1 = (TextView) findViewById(R.id.day_p1_temperature);
                    temp_jourp1.setText(temperature);

                    weather = resultat.getJSONArray("weather").getJSONObject(0).getString("main");
                    TextView weather_jourp1 = (TextView) findViewById(R.id.day_p1_weather);
                    weather_jourp1.setText(weather);

                    ImageView imgView1 = (ImageView) findViewById(R.id.day_p1_weather_image);


                    switch (weather) {
                        case "Clear":
                            imgView1.setImageResource(R.drawable.clear);
                            break;
                        case "Clouds":
                            imgView1.setImageResource(R.drawable.clouds);
                            break;
                        case "Rain":
                            imgView1.setImageResource(R.drawable.rain);
                            break;
                        case "Snow":
                            imgView1.setImageResource(R.drawable.snow);
                            break;
                        default:
                            imgView.setImageResource(R.drawable.snow);
                    }

                    resultat = list.getJSONObject(16);
                    temp = resultat.getJSONObject("main").getDouble("temp");
                    temp -= 273.15;
                    temperature = String.valueOf(temp).substring(0, 3) + " °C";
                    TextView temp_jourp2 = (TextView) findViewById(R.id.day_p2_temperature);
                    temp_jourp2.setText(temperature);

                    weather = resultat.getJSONArray("weather").getJSONObject(0).getString("main");
                    TextView weather_jourp2 = (TextView) findViewById(R.id.day_p2_weather);
                    weather_jourp2.setText(weather);

                    ImageView imgView2 = (ImageView) findViewById(R.id.day_p2_weather_image);


                    switch (weather) {
                        case "Clear":
                            imgView2.setImageResource(R.drawable.clear);
                            break;
                        case "Clouds":
                            imgView2.setImageResource(R.drawable.clouds);
                            break;
                        case "Rain":
                            imgView2.setImageResource(R.drawable.rain);
                            break;
                        case "Snow":
                            imgView2.setImageResource(R.drawable.snow);
                            break;
                        default:
                            imgView2.setImageResource(R.drawable.snow);
                    }

                    resultat = list.getJSONObject(24);
                    temp = resultat.getJSONObject("main").getDouble("temp");
                    temp -= 273.15;
                    temperature = String.valueOf(temp).substring(0, 3) + " °C";
                    TextView temp_jourp3 = (TextView) findViewById(R.id.day_p3_temperature);
                    temp_jourp3.setText(temperature);

                    weather = resultat.getJSONArray("weather").getJSONObject(0).getString("main");
                    TextView weather_jourp3 = (TextView) findViewById(R.id.day_p3_weather);
                    weather_jourp3.setText(weather);

                    ImageView imgView3 = (ImageView) findViewById(R.id.day_p3_weather_image);


                    switch (weather) {
                        case "Clear":
                            imgView3.setImageResource(R.drawable.clear);
                            break;
                        case "Clouds":
                            imgView3.setImageResource(R.drawable.clouds);
                            break;
                        case "Rain":
                            imgView3.setImageResource(R.drawable.rain);
                            break;
                        case "Snow":
                            imgView3.setImageResource(R.drawable.snow);
                            break;
                        default:
                            imgView2.setImageResource(R.drawable.snow);
                    }

                    resultat = list.getJSONObject(32);
                    temp = resultat.getJSONObject("main").getDouble("temp");
                    temp -= 273.15;
                    temperature = String.valueOf(temp).substring(0, 3) + " °C";
                    TextView temp_jourp4 = (TextView) findViewById(R.id.day_p4_temperature);
                    temp_jourp4.setText(temperature);

                    weather = resultat.getJSONArray("weather").getJSONObject(0).getString("main");
                    TextView weather_jourp4 = (TextView) findViewById(R.id.day_p4_weather);
                    weather_jourp4.setText(weather);

                    ImageView imgView4 = (ImageView) findViewById(R.id.day_p4_weather_image);


                    switch (weather) {
                        case "Clear":
                            imgView4.setImageResource(R.drawable.clear);
                            break;
                        case "Clouds":
                            imgView4.setImageResource(R.drawable.clouds);
                            break;
                        case "Rain":
                            imgView4.setImageResource(R.drawable.rain);
                            break;
                        case "Snow":
                            imgView4.setImageResource(R.drawable.snow);
                            break;
                        default:
                            imgView4.setImageResource(R.drawable.snow);
                    }
                }else{
                    Intent intent = new Intent(MeteoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}
