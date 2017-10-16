package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import dominguessolutions.paul_decisionmaker.R;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.TextUtils;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private DecisionMakerPresenter decisionMakerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decisionMakerPresenter = new DecisionMakerPresenter();

        ImageButton btnAddOption = (ImageButton)findViewById(R.id.btnAddOption);
        btnAddOption.setOnClickListener(this);
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
}
