package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dominguessolutions.paul_decisionmaker.R;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.Shake;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.ShakeAccelerometer;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.TextUtils;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity implements OnClickListener, SensorListener {

    private DecisionMakerPresenter decisionMakerPresenter;
    private Shake shake;
    private DecisionDialog decisionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decisionMakerPresenter = new DecisionMakerPresenter();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
        shake = new ShakeAccelerometer(sensorManager);

        ImageButton btnAddOption = (ImageButton)findViewById(R.id.btnAddOption);
        btnAddOption.setOnClickListener(this);

        final DecisionDialog decisionDialogFinal = new DecisionDialog(this);
        decisionDialog = decisionDialogFinal;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddOption) {
            btnAddOptionClick();
            return;
        }
    }

    private void btnAddOptionClick() {
        EditText txtNewOption = (EditText)findViewById(R.id.txtNewOption);
        String newOption = txtNewOption.getText().toString();

        if (isValidOption(newOption)) {
            decisionMakerPresenter.addOption(newOption);
            addOptionToScreen();
            txtNewOption.getText().clear();
        }
    }

    private boolean isValidOption(String newOption) {
        if (TextUtils.isValidText(newOption)) {
            return true;
        }

        notifyUserToTypeValidText();
        return false;
    }

    private void notifyUserToTypeValidText() {
        Toast.makeText(this, R.string.type_valid_text, Toast.LENGTH_LONG).show();
    }

    private void addOptionToScreen() {
        ListView listOptions = (ListView) findViewById(R.id.listOptions);
        ArrayAdapter<String> listOptionsAdapter = new ArrayAdapter<String>(this, simple_list_item_1, decisionMakerPresenter.getOptions());
        listOptions.setAdapter(listOptionsAdapter);
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (shake.shakeDetected(sensor, values)) {
            if (haveOptionsToDecide(decisionMakerPresenter.getOptions())) {
                showDecision();
            }
        }
    }

    private void showDecision() {
        if (!decisionDialog.isDialogOpen()) {
            decisionDialog.setDecision(decisionMakerPresenter.makeDecision());
            decisionDialog.show();
        }
    }

    private boolean haveOptionsToDecide(List<String> options) {
        return !options.isEmpty();
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}