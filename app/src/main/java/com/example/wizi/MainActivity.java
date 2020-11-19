package com.example.wizi;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ai.Train;
import sensor.LinearAcceleration;

public class MainActivity extends AppCompatActivity {

    private TextView alert;
    private View pin;
    private LinearAcceleration linearAcceleration;

    private Train networkTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alert = findViewById(R.id.text_alert);
        pin = findViewById(R.id.center_pin);

        networkTrainer = new Train();

        linearAcceleration = new LinearAcceleration(this);

        linearAcceleration.setListener(new LinearAcceleration.Listener() {
            @Override
            public void onAcceleration(float ax, float ay, float az) {

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                {
                    pin.setTranslationX((ax*100)/9);
                    pin.setTranslationY((az*100)/9);

                    if(ax<-8 || ax>8 || az<-8 || az>8)
                    {
                        alert.setText("Attention"+"\n"+"conduite brutale");
                    }
                    else
                    {
                        alert.setText("");
                    }
                }

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                {
                    pin.setTranslationX((ay*100)/9);
                    pin.setTranslationY((az*100)/9);

                    if(ay<-8 || ax>8 || ay<-8 || az>8)
                    {
                        alert.setText("Attention"+"\n"+"conduite brutale");
                    }
                    else
                    {
                        alert.setText("");
                    }
                }
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        linearAcceleration.register();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        linearAcceleration.unregister();
    }
}