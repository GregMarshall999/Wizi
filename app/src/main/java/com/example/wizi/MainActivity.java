package com.example.wizi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Core.Accelerometer;
import Core.Gyroscope;
import Core.LinearAcceleration;

public class MainActivity extends AppCompatActivity {

    private TextView textViewAcc;
    private TextView textViewGyro;
    private TextView textViewLin;
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private LinearAcceleration linearAcceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAcc = findViewById(R.id.text_accelerometer);
        textViewGyro = findViewById(R.id.text_gyroscope);
        textViewLin = findViewById(R.id.text_Linear);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        linearAcceleration = new LinearAcceleration(this);

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {

                textViewAcc.setText(tx+"\n"+ty+"\n"+tz);

                //getWindow().getDecordView().setBackgroundColor(Color.RED);
            }
        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {

                textViewGyro.setText(rx+"\n"+ry+"\n"+rz);

            }
        });

        linearAcceleration.setListener(new LinearAcceleration.Listener() {
            @Override
            public void onAcceleration(float ax, float ay, float az) {

                textViewLin.setText(ax+"\n"+ay+"\n"+az);

            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        accelerometer.register();
        gyroscope.register();
        linearAcceleration.register();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();
        linearAcceleration.unregister();
    }
}