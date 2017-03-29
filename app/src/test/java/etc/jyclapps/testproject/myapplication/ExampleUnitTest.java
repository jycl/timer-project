package etc.jyclapps.testproject.myapplication;

import android.util.Log;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void string_isCorrect() throws Exception {
        String hour = "1";
        int day = 1;

        //System.out.println(String.format("%02s", hour));
        System.out.println(String.valueOf(String.format(Locale.CANADA,"%02d", day)));

        assertEquals(4, 2 + 2);
    }

//
    Date start_date = new Date();
    Date current_date = new Date();
    Date end_date = new Date();
//
//    DateFormat df = new SimpleDateFormat("");
//
//    Calendar cal =
//
//    @Test
//    public void additionOfDates_isCorrect() throws Exception {
//        assertEquals(4, end_date.getTime() + start_date.getTime());
//    }

    @Test
    public void division_isCorrect() throws Exception {
        assertEquals(0.5, 43200000/79633310.0d, 0.01);
    }

    @Test
    public void rainwaterCaughtAt() {
        System.out.print(String.valueOf(5/2));
    }


}