package dominguessolutions.paul_decisionmaker.decisionmaker.model;

/**
 * Created by lucas on 15/10/2017.
 */

public class TextUtils {

    /**
     * Check if text is not null and not empty
     * @param text  string to be validated
     * @return      valid text
     */
    public static boolean isValidText(String text) {
        return  text != null && !text.isEmpty();
    }
}
