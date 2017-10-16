package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import java.util.List;

/**
 * Created by lucas.domingues on 28/09/2017.
 */

public interface Decision {

    /**
     * Method returns randomly one option from a list
     * @param optionsToDecide   list with many strings containing option to be choiced
     * @return                  one option choiced ramdomly
     */
    String makeDecision(List<String> optionsToDecide);
}
