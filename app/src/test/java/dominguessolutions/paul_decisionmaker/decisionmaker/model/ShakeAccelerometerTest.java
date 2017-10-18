package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import android.hardware.SensorManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lucas on 17/10/2017.
 */
public class ShakeAccelerometerTest {
    private ShakeAccelerometer shakeAccelerometer;

    @Before
    public void setUp() {
        SensorManager sensorManager = null;
        shakeAccelerometer = new ShakeAccelerometer(sensorManager);
    }

    @Test
    public void mustNotDetectShakeWhenSensorIsNotAccelerometer() throws Exception {
        assertFalse(shakeAccelerometer.shakeDetected(0, new float[]{0}));
    }
}