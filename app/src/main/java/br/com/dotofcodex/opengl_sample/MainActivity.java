package br.com.dotofcodex.opengl_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import br.com.dotofcodex.opengl_sample.view.OpenGLView;

/**
 * More information in the following link:
 *  https://dev.to/elasticrash/getting-started-with-native-opengl-android-app-19e7
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final static int SCALE = 4;
    private OpenGLView openGl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openGl = findViewById(R.id.opengl_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manager.unregisterListener(this);
        openGl.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        openGl.onResume();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("APP", "new sensor event");
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = Math.round(event.values[0] * 100.0) / 100f;
            float y = Math.round(event.values[1] * 100.0) / 100f;
            if (openGl.renderer.objectsReady) {
                openGl.renderer.getCircle().calculatePoints(x /    SCALE, y / SCALE, 0.1f, 55);
                openGl.requestRender();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
