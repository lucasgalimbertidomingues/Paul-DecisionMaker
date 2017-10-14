package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dominguessolutions.paul_decisionmaker.R;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DecisionMakerPresenter decisionMakerPresenter = new DecisionMakerPresenter();
        setContentView(R.layout.activity_main);
    }
}
