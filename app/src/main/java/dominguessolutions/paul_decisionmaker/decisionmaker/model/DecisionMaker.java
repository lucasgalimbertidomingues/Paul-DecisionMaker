package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by lucas.domingues on 28/09/2017.
 */

public class DecisionMaker implements Decision {
    @Override
    public String makeDecision(List<String> optionsToDecide) {
        if(!optionsToDecide.isEmpty()) {
            return randomOptions(optionsToDecide);
        }
        return null;
    }

    private String randomOptions(List<String> optionsToDecide) {
        long seed = new Date().getTime();
        Random random = new Random(seed);
        int index = random.nextInt(optionsToDecide.size());
        return optionsToDecide.get(index);
    }
}
