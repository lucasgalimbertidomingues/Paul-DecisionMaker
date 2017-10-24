package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import dominguessolutions.paul_decisionmaker.R;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.OperationEnum;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

/**
 * Created by lucas on 18/10/2017.
 */

class ListOptionsAdapter extends BaseAdapter implements ListAdapter {
    private DecisionMakerPresenter decisionMakerPresenter;
    private Activity activity;
    private OperationEnum operationEnum;
    private int indexChoiced;

    /**
     * Constructor of class that represents a adapter to be used to build the list containing options to be decided
     * @param decisionMakerPresenter presenter to manipulates the options
     * @param activity activity who calls the adapter
     */
    ListOptionsAdapter(DecisionMakerPresenter decisionMakerPresenter, Activity activity) {
        this.decisionMakerPresenter = decisionMakerPresenter;
        this.activity = activity;
        this.operationEnum = OperationEnum.INSERT;
    }

    @Override
    public int getCount() {
        return decisionMakerPresenter.getOptions().size();
    }

    @Override
    public Object getItem(int pos) {
        return decisionMakerPresenter.getOptions().get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View optionToDecideView = convertView;
        if (optionToDecideView == null) {
            optionToDecideView = inflateLayoutToView();
        }

        TextView txtOption = (TextView)optionToDecideView.findViewById(R.id.txtOption);
        txtOption.setText(decisionMakerPresenter.getOptions().get(position));
        txtOption.setOnClickListener(prepareListenerToEditOption(position));

        ImageButton deleteBtn = (ImageButton) optionToDecideView.findViewById(R.id.btnDeleteOption);
        deleteBtn.setOnClickListener(prepareListenerToDeleteOption(position));

        ImageButton editBtn = (ImageButton) optionToDecideView.findViewById(R.id.btnEditOption);
        editBtn.setOnClickListener(prepareListenerToEditOption(position));

        return optionToDecideView;
    }

    private OnClickListener prepareListenerToEditOption(final int position) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                operationEnum = OperationEnum.EDIT;
                EditText txtOption = (EditText)activity.findViewById(R.id.txtSubmitOption);
                txtOption.setText(decisionMakerPresenter.getOptions().get(position));
                indexChoiced = position;
            }
        };
    }

    @NonNull
    private OnClickListener prepareListenerToDeleteOption(final int position) {
        return new OnClickListener(){
            @Override
            public void onClick(View v) {
                decisionMakerPresenter.removeOption(position);
                operationEnum = OperationEnum.INSERT;
                notifyDataSetChanged();
            }
        };
    }

    private View inflateLayoutToView() {
        View optionToDecideView;LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
        optionToDecideView = inflater.inflate(R.layout.option_to_decide, null);
        return optionToDecideView;
    }

    private Object getLayoutInflater() {
        return activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public OperationEnum getOperationEnum() {
        return operationEnum;
    }

    public void setOperationEnum(OperationEnum operationEnum) {
        this.operationEnum = operationEnum;
    }

    public int getIndexChoiced() {
        return indexChoiced;
    }
}
