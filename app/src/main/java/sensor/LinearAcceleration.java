package sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/*
* Cette classe constitue un outil d'accès aux données d'accelerations linéaires*/
public class LinearAcceleration
{
    //Cette interface sert d'observateurs pour éviter un surplus de notification d'atteindre la MainActivity
    public interface Listener
    {
        void onAcceleration(float ax, float ay, float az);
    }

    private Listener listener;

    public void setListener(Listener l)
    {
        listener = l;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    /*
    * Ce contructeur met en place l'interface d'ecoute des senseurs pour nous donner ses valeurs */
    public LinearAcceleration(Context context)
    {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener != null)
                {
                    listener.onAcceleration(event.values[0], event.values[1], event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    //Ce void nous permet de reprendre notre observateur au retablissement de l'application
    public void register()
    {
        sensorManager.registerListener(sensorEventListener, sensor, sensorManager.SENSOR_DELAY_FASTEST);
    }

    //Ce void nous permet de suprimer notre observateur a la pause de l'application
    public void unregister()
    {
        sensorManager.unregisterListener(sensorEventListener);
    }
}
