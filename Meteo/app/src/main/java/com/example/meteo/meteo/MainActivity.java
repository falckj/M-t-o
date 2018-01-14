package com.example.meteo.meteo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText cityEditText = null;
    private Button submitCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityEditText = (EditText) findViewById(R.id.city_field);
        submitCity = (Button) findViewById(R.id.submit_city);

        submitCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeteoActivity.class);
                intent.putExtra("city", cityEditText.getText());
                startActivity(intent);
                finish();
            }
        });

    }
}
