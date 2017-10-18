package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by lucas on 16/10/2017.
 */

public class ShakeAccelerometer implements Shake{
    private SensorManager sensorManager;

    private static final int SHAKE_THRESHOLD = 800;
    private long lastUpdate;
    private float last_x, last_y, last_z;

    public ShakeAccelerometer(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public boolean shakeDetected(int sensor, float[] coordinates) {
        if (sensorDetectedIsAcelerometer(sensor)) {
            long curTime = System.currentTimeMillis();
            if (canReadNewShake(curTime)) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float x = coordinates[SensorManager.DATA_X];
                float y = coordinates[SensorManager.DATA_Y];
                float z = coordinates[SensorManager.DATA_Z];

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                last_x = x;
                last_y = y;
                last_z = z;

                if (shakeAppliedIsEnough(speed)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shakeAppliedIsEnough(float speed) {
        return speed > SHAKE_THRESHOLD;
    }

    private boolean canReadNewShake(long curTime) {
        return (curTime - lastUpdate) > 100;
    }

    private boolean sensorDetectedIsAcelerometer(int sensor) {
        return sensor == SensorManager.SENSOR_ACCELEROMETER;
    }
}
