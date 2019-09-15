package com.app.oshadhi.sensor_read_app;

        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class SensorApp extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "SensorApp";
    SensorManager sensorManager;
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_app);

        /* get system service */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        /* set initial text on label */
        final TextView textView =findViewById(R.id.lbl);
        textView.setText("Sensor Offline");

        final Button btn = findViewById(R.id.btn);

        /* execute button click method */
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onSensorStatusChanged(view); // execute code on toggle on status button

            }
        });
    }

    private void onSensorStatusChanged(View view) {

        Button btn = (Button)findViewById(R.id.btn);
        String text;

        /* check the button readings */
        if(btn.getText().equals("START")){

            text= "STOP";
            btn.setText(text);
            /* get sensor */
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            /* register listener */
            sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);

        }
        else if(btn.getText().equals("STOP")){

            text= "START";
            btn.setText(text);

            /* unregister listener */
            sensorManager.unregisterListener(this);

            TextView textView =findViewById(R.id.lbl);

            text = "Sensor Offline";
            textView.setText(text);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.e(TAG, "onSensorChanged: "+ event.values[0] + " Y :" + event.values[1] + " Z :"+ event.values[2]);

        /* visualize sensor readings on the screen*/
        TextView textView =findViewById(R.id.lbl);
        String readings="Accelerometer Readings"+ "\n"+ "x value : " + event.values[0]+ "\n" + "y value :"+ event.values[1]+ "\n" + "z value :"+ event.values[2];
        textView.setText(readings);


    }
}

