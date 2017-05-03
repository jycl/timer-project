package etc.jyclapps.testproject.myapplication.tools;

import java.util.Locale;

/**
 * Created by Josh 2 on 4/16/2017.
 */

public class StringIntFormatter {
    /**
     * Method to convert integer into a string with correct number of digits, e.g. integer 24, digits 4 => 0024
     * @param integer The integer value input to convert to string
     * @param digits The number of digit placeholders needed in string
     * @return String of the integer with correct formatting
     */
    public static String formatIntToString(int integer, int digits) {
        if(digits != 0) {
            return String.format(Locale.ENGLISH, "%0" + digits + "d", integer);
        } else {
            return String.valueOf(integer);
        }
    }
}
