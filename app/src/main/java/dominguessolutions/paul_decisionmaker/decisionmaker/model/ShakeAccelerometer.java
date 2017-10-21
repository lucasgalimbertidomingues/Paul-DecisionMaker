package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import android.hardware.SensorManager;

/**
 * Created by lucas on 16/10/2017.
 */

public class ShakeAccelerometer implements Shake{

    private static final int SHAKE_THRESHOLD = 800;
    private long lastUpdate;
    private float last_x, last_y, last_z;

    @Override
    public boolean shakeDetected(int sensor, float[] coordinates) {
        if (sensorDetectedIsAccelerometer(sensor)) {
            long currentTime = System.currentTimeMillis();
            if (canReadNewShake(currentTime)) {
                long diffTime = (currentTime - lastUpdate);
                lastUpdate = currentTime;

                float x = coordinates[SensorManager.DATA_X];
                float y = coordinates[SensorManager.DATA_Y];
                float z = coordinates[SensorManager.DATA_Z];

                float intensity = calculateIntensityOfShake(diffTime, x, y, z);
                updateCoordinates(x, y, z);

                if (shakeAppliedIsEnough(intensity)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateCoordinates(float x, float y, float z) {
        last_x = x;
        last_y = y;
        last_z = z;
    }

    private float calculateIntensityOfShake(long diffTime, float x, float y, float z) {
        return calculateDiffCoordinates(x, y, z) / diffTime * 10000;
    }

    private float calculateDiffCoordinates(float x, float y, float z) {
        return Math.abs(sumActualCoordinates(x, y, z) - sumLastCoordinates());
    }

    private float sumActualCoordinates(float x, float y, float z) {
        return x + y + z;
    }

    private float sumLastCoordinates() {
        return last_x + last_y + last_z;
    }

    private boolean shakeAppliedIsEnough(float speed) {
        return speed > SHAKE_THRESHOLD;
    }

    private boolean canReadNewShake(long curTime) {
        return (curTime - lastUpdate) > 100;
    }

    private boolean sensorDetectedIsAccelerometer(int sensor) {
        return sensor == SensorManager.SENSOR_ACCELEROMETER;
    }
}
