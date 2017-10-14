package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by lucas.domingues on 28/09/2017.
 */

public class DecisionMakerTest {

    private DecisionMaker decisionMaker;

    @Before
    public void setUp() {
        decisionMaker = new DecisionMaker();
    }

    @Test
    public void must_decide_pizza_the_unique_option_to_decide() throws Exception {
        List<String> optionsToDecide = new ArrayList<>();
        optionsToDecide.add("Pizza");
        assertEquals("Pizza", decisionMaker.makeDecision(optionsToDecide));
    }

    @Test
    public void must_return_null_when_dont_have_options_to_decide() throws Exception {
        List<String> optionsToDecide = new ArrayList<>();
        assertNull(decisionMaker.makeDecision(optionsToDecide));
    }

    @Test
    public void must_not_return_null_when_have_more_than_one_option_to_decide() throws Exception {
        List<String> optionsToDecide = new ArrayList<>();
        optionsToDecide.add("Pizza");
        optionsToDecide.add("Bread");
        optionsToDecide.add("Hamburger");
        assertNotNull(decisionMaker.makeDecision(optionsToDecide));
    }
}
