package dominguessolutions.paul_decisionmaker.decisionmaker.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lucas on 16/10/2017.
 */
public class TextUtilsTest {
    @Test
    public void isValidTextWhenNotNullAndNotEmpty() throws Exception {
        String textNotNullAndNotEmpty = "Text";
        assertTrue(TextUtils.isValidText(textNotNullAndNotEmpty));
    }

    @Test
    public void isNotValidTextWhenNullOrEmpty() throws Exception {
        String textNull = null;
        assertFalse(TextUtils.isValidText(textNull));

        String textEmpty = "";
        assertFalse(TextUtils.isValidText(textEmpty));
    }
}