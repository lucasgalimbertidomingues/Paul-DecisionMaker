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

    /**
     * Constructor of class used to manage de option and decision
     */
    public DecisionMakerPresenter() {
        optionsToDecide = new ArrayList<>();
    }

    /**
     * Presenter method responsible to call the decision based on options
     * @return string containing the decision
     */
    public String makeDecision() {
        Decision decision = new DecisionMaker();
        return decision.makeDecision(optionsToDecide);
    }

    /**
     * Presenter method responsible to add new option to be decided
     * @param option new option to be decided
     */
    public void addOption(String option) {
        optionsToDecide.add(option);
    }

    /**
     * Presenter method responsible to remove an option
     * @param index position of option to be removed
     */
    public void removeOption(int index) {
        optionsToDecide.remove(index);
    }

    public void editOption(int index, String option) {
        optionsToDecide.set(index, option);
    }

    /**
     * Presenter method responsible to get all options
     * @return set of string containg all options
     */
    public List<String> getOptions() { return optionsToDecide; }
}
