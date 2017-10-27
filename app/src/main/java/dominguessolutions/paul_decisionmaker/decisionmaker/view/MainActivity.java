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
import dominguessolutions.paul_decisionmaker.decisionmaker.model.OperationEnum;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.Shake;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.ShakeAccelerometer;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.TextUtils;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

public class MainActivity extends AppCompatActivity implements OnClickListener, SensorListener {

    private DecisionMakerPresenter decisionMakerPresenter;
    private Shake shake;
    private DecisionDialog decisionDialog;
    private ListOptionsAdapter listOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decisionMakerPresenter = new DecisionMakerPresenter();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
        shake = new ShakeAccelerometer();

        ImageButton btnAddOption = (ImageButton)findViewById(R.id.btnSubmitOption);
        btnAddOption.setOnClickListener(this);

        Button btnMakeDecision = (Button)findViewById(R.id.btnMakeDecision);
        btnMakeDecision.setOnClickListener(this);

        decisionDialog = new DecisionDialog(this);
        listOptionsAdapter = new ListOptionsAdapter(decisionMakerPresenter, this);
        addOptionToScreen();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSubmitOption) {
            btnSubmitOptionClick();
            return;
        }
        if(v.getId() == R.id.btnMakeDecision) {
            btnMakeDecisionClick();
        }
    }

    private void btnMakeDecisionClick() {
        makeDecision(true);
    }

    private void btnSubmitOptionClick() {
        EditText txtOption = (EditText)findViewById(R.id.txtSubmitOption);
        String option = txtOption.getText().toString();

        if (isValidOption(option)) {
            setOption(option);
            addOptionToScreen();
            updateOperationStatusToInsert();
            txtOption.getText().clear();
            cleanHintToInsertOptionToStart();
        }
    }

    private void setOption(String option) {
        if (isNewOption()) {
            decisionMakerPresenter.addOption(option);
        } else {
            decisionMakerPresenter.editOption(listOptionsAdapter.getIndexChoiced(), option);
        }
    }

    private void updateOperationStatusToInsert() {
        listOptionsAdapter.setOperationEnum(OperationEnum.INSERT);
    }

    private boolean isNewOption() {
        return listOptionsAdapter.getOperationEnum().equals(OperationEnum.INSERT);
    }

    private void cleanHintToInsertOptionToStart() {
        LinearLayout layoutInsertOptionToStartLayout = (LinearLayout)findViewById(R.id.insertOptionToStartLayout);
        layoutInsertOptionToStartLayout.setVisibility(View.GONE);
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
        listOptions.setAdapter(listOptionsAdapter);
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (shake.shakeDetected(sensor, values)) {
            makeDecision(false);
        }
    }

    private void makeDecision(boolean decisionMadeByButton) {
        if (haveOptionsToDecide(decisionMakerPresenter.getOptions())) {
            showDecision();
        } else if (decisionMadeByButton) {
            notifyUserToSetSomeOption();
        }
    }

    private void notifyUserToSetSomeOption() {
        Toast.makeText(this, R.string.insert_some_option, Toast.LENGTH_SHORT).show();
    }

    private void showDecision() {
        if (!decisionDialog.isShowing()) {
            decisionDialog.setDecision(decisionMakerPresenter.makeDecision());
            if(!isActivityWasDestroyed()) {
                decisionDialog.show();
            }
        }
    }

    private boolean isActivityWasDestroyed() {
        return this.isFinishing();
    }

    private boolean haveOptionsToDecide(List<String> options) {
        return !options.isEmpty();
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}