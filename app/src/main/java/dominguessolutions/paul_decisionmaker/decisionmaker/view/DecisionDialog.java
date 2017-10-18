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
public class DecisionDialog extends Dialog implements View.OnClickListener {
    private String decision;
    private boolean dialogOpen;

    public DecisionDialog(@NonNull Context context) {
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
            return;
        }
    }

    private void btnOkClick() {
        this.dismiss();
    }

    @Override
    public void show() {
        prepareDialogToShow();
        super.show();
        setDialogOpen(true);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setDialogOpen(false);
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public boolean isDialogOpen() {
        return dialogOpen;
    }

    private void setDialogOpen(boolean dialogOpen) {
        this.dialogOpen = dialogOpen;
    }
}
