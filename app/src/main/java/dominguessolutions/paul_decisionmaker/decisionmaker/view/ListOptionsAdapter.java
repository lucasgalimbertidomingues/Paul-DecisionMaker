package dominguessolutions.paul_decisionmaker.decisionmaker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import dominguessolutions.paul_decisionmaker.R;
import dominguessolutions.paul_decisionmaker.decisionmaker.presenter.DecisionMakerPresenter;

/**
 * Created by lucas on 18/10/2017.
 */

class ListOptionsAdapter extends BaseAdapter implements ListAdapter {
    private DecisionMakerPresenter decisionMakerPresenter;
    private Context context;

    /**
     * Constructor of class that represents a adapter to be used to build the list containing options to be decided
     * @param decisionMakerPresenter
     * @param context
     */
    ListOptionsAdapter(DecisionMakerPresenter decisionMakerPresenter, Context context) {
        this.decisionMakerPresenter = decisionMakerPresenter;
        this.context = context;
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

        ImageButton deleteBtn = (ImageButton) optionToDecideView.findViewById(R.id.btnDeleteOption);
        deleteBtn.setOnClickListener(prepareListenerToDeleteOption(position));

        return optionToDecideView;
    }

    @NonNull
    private OnClickListener prepareListenerToDeleteOption(final int position) {
        return new OnClickListener(){
            @Override
            public void onClick(View v) {
                decisionMakerPresenter.removeOption(position);
                notifyDataSetChanged();
            }
        };
    }

    private View inflateLayoutToView() {
        View optionToDecideView;LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        optionToDecideView = inflater.inflate(R.layout.option_to_decide, null);
        return optionToDecideView;
    }
}
