package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import dominguessolutions.paul_decisionmaker.R;

/**
 * Created by lucas on 17/10/2017.
 */
class DecisionDialog extends Dialog implements View.OnClickListener {
    private String decision;

    /**
     * Constructor of class that represents the dialog that show to user the result of output with decision
     * @param context context of activity that uses the dialog
     */
    DecisionDialog(@NonNull Context context) {
        super(context);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.dialog_decision);
    }

    private void prepareDialogToShow() {
        prepareTxtDecision();
        prepareBtnOk();
    }

    private void prepareBtnOk() {
        Button btnOk = (Button)findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
    }

    private void prepareTxtDecision() {
        TextView txtDecision = (TextView)findViewById(R.id.txtDecision);
        txtDecision.setText(this.decision);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnOk) {
            btnOkClick();
        }
    }

    private void btnOkClick() {
        this.dismiss();
    }

    @Override
    public void show() {
        prepareDialogToShow();
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    /**
     * Set the decision on the dialog
     * @param decision  Text containing the decision
     */
    void setDecision(String decision) {
        this.decision = decision;
    }
}
