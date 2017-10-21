package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import org.junit.Before;
import org.junit.Test;

import static android.hardware.SensorManager.SENSOR_LIGHT;
import static android.hardware.SensorManager.SENSOR_ACCELEROMETER;

import static org.junit.Assert.*;

/**
 * Created by lucas on 17/10/2017.
 */
public class ShakeAccelerometerTest {
    private ShakeAccelerometer shakeAccelerometer;

    @Before
    public void setUp() {
        shakeAccelerometer = new ShakeAccelerometer();
    }

    @Test
    public void mustNotDetectShakeWhenSensorIsNotAccelerometer() {
        assertFalse(shakeAccelerometer.shakeDetected(SENSOR_LIGHT, generateCoordinatesToDetectShake()));
    }

    @Test
    public void mustNotDetectShakeWhenTimeBetweenShakesIsTooShort() {
        shakeAccelerometer.shakeDetected(SENSOR_ACCELEROMETER, generateCoordinatesToDetectShake());
        assertFalse(shakeAccelerometer.shakeDetected(SENSOR_ACCELEROMETER, generateCoordinatesToDetectShake()));
    }

    @Test
    public void mustDetectShake() {
        assertTrue(shakeAccelerometer.shakeDetected(SENSOR_ACCELEROMETER, generateCoordinatesToDetectShake()));
    }

    private float[] generateCoordinatesToDetectShake() {
        return new float[]{99999999999999f,99999999999999f,99999999999999f};
    }
}