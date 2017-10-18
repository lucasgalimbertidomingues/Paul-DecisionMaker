package dominguessolutions.paul_decisionmaker.decisionmaker.model;

/**
 * Created by lucas on 17/10/2017.
 */

public interface Shake {

    /**
     * Identify if device is shaked by user
     *
     * @param sensor        sensor that shake will read (ex.: Accelerometer)
     * @param coordinates   coordinates provided by SensorListener
     * @return              if device is shaked
     */
    boolean shakeDetected(int sensor, float[] coordinates);
}
