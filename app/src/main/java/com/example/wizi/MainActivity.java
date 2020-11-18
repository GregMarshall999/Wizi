package com.example.wizi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Core.Accelerometer;
import Core.Gyroscope;

public class MainActivity extends AppCompatActivity {

    private TextView textViewAcc;
    private TextView textViewGyro;
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAcc = findViewById(R.id.text_accelerometer);
        textViewGyro = findViewById(R.id.text_gyroscope);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);

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


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();
    }
}