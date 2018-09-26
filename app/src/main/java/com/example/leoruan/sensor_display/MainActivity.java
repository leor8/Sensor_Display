package com.example.leoruan.sensor_display;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mySensorManager;
    private Sensor myAccel;
    private float[] acc_val;
    private EditText acc_x, acc_y, acc_z;

    private TextView sensor_list_display;
    List<Sensor> sensor_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        myAccel = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acc_x = findViewById(R.id.editText);
        acc_y = findViewById(R.id.editText2);
        acc_z = findViewById(R.id.editText3);
        sensor_list_display = findViewById(R.id.textView);

        sensor_list = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        for(int i = 0; i < sensor_list.size(); i++) {
            sensor_list_display.append("\n" + sensor_list.get(i).getName());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent evt) {
        int sensor_type = evt.sensor.getType();

        if(sensor_type == Sensor.TYPE_ACCELEROMETER) {
            acc_val = evt.values;
            acc_x.setText("AccelX: " + acc_val[0]);
            acc_y.setText("AccelY: " + acc_val[0]);
            acc_z.setText("AccelZ: " + acc_val[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor s, int i) {
    }


    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, myAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mySensorManager.unregisterListener(this);
        super.onPause();
    }


}
