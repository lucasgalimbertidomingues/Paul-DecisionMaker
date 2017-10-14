package dominguessolutions.paul_decisionmaker.decisionmaker.presenter;

import java.util.ArrayList;
import java.util.List;

import dominguessolutions.paul_decisionmaker.decisionmaker.model.Decision;
import dominguessolutions.paul_decisionmaker.decisionmaker.model.DecisionMaker;

/**
 * Created by lucas.domingues on 28/09/2017.
 */

public class DecisionMakerPresenter {
    private List<String> optionsToDecide;

    public DecisionMakerPresenter() {
        optionsToDecide = new ArrayList<>();
    }

    public String makeDecision() {
        Decision decision = new DecisionMaker();
        return decision.makeDecision(optionsToDecide);
    }

    public void addOption(String option) {
        optionsToDecide.add(option);
    }

    public List<String> getOptions() { return optionsToDecide; }
}
