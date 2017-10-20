package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dominguessolutions.paul_decisionmaker.R;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.Shake;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.ShakeAccelerometer;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.TextUtils;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

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

        Button btnMakeDecision = (Button)findViewById(R.id.btnMakeDecision);
        btnMakeDecision.setOnClickListener(this);

        decisionDialog = new DecisionDialog(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddOption) {
            btnAddOptionClick();
            return;
        }
        if(v.getId() == R.id.btnMakeDecision) {
            btnMakeDecisionClick();
        }
    }

    private void btnMakeDecisionClick() {
        makeDecision();
    }

    private void btnAddOptionClick() {
        EditText txtNewOption = (EditText)findViewById(R.id.txtNewOption);
        String newOption = txtNewOption.getText().toString();

        if (isValidOption(newOption)) {
            decisionMakerPresenter.addOption(newOption);
            addOptionToScreen();
            txtNewOption.getText().clear();
            LinearLayout layoutInsertOptionToStartLayout = (LinearLayout)findViewById(R.id.insertOptionToStartLayout);
            layoutInsertOptionToStartLayout.setVisibility(View.GONE);
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
        List<String> options = decisionMakerPresenter.getOptions();
        ListOptionsAdapter listOptionsAdapter = new ListOptionsAdapter(options, this);

        ListView listOptions = (ListView) findViewById(R.id.listOptions);
        listOptions.setAdapter(listOptionsAdapter);
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (shake.shakeDetected(sensor, values)) {
            makeDecision();
        }
    }

    private void makeDecision() {
        if (haveOptionsToDecide(decisionMakerPresenter.getOptions())) {
            showDecision();
        } else {
            notifyUserToSetSomeOption();
        }
    }

    private void notifyUserToSetSomeOption() {
        Toast.makeText(this, R.string.insert_some_option, Toast.LENGTH_LONG).show();
    }

    private void showDecision() {
        if (!decisionDialog.isShowing()) {
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