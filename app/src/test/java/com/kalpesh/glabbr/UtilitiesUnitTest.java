package com.kalpesh.glabbr;

import com.kalpesh.glabbr.Utills.Utilities;
import com.kalpesh.glabbr.models.MessageObject;
import com.kalpesh.glabbr.models.StaticData;
import com.kalpesh.glabbr.models.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilitiesUnitTest {

    private static final Pattern VALID_TIME_FORMAT = Pattern.compile("^[0-9]+:[0-9]+ [A-Z]", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_DATE_FORMAT_MONTH_AND_TIME =
            Pattern.compile("^[A-Z]+ [0-9]+ [0-9]+:[0-9]+ [A-Z]", Pattern.CASE_INSENSITIVE);

    /*
     * Ensure weather function returns valid time string
     */
    @Test
    public void timeStringTest() {
        long millis = System.currentTimeMillis();
        String dateString = Utilities.timeString(millis, Locale.getDefault());
        Matcher matcher = VALID_TIME_FORMAT.matcher(dateString);
        Assert.assertThat(String.format("Invalid Time format"), matcher.find(), is(true));
    }

    /*
     * Ensure weather function returns valid Month day and time string string
     */
    @Test
    public void validMonthDateTimeFormat() {
        long millis = System.currentTimeMillis();
        String dateString = Utilities.timeString(millis, Locale.getDefault());
        Matcher matcher = VALID_DATE_FORMAT_MONTH_AND_TIME.matcher(dateString);
        Assert.assertThat(String.format("Invalid Time format"), matcher.find(), is(true));
    }

    /*
     * Ensure weather function returns valid user data of that id is passed
     */
    @Test
    public void getValidUserById() {
        User user = StaticData.getInstance().getUsers().get(0);
        assertNull("User object is null", Utilities.getUserById(user.getId()));
        assertEquals("Coud not getting valid data for %d",user.getId(), Utilities.getUserById(user.getId()).getId(), user.getId());
    }

    /*
     * Ensure weather function returns valid message data of that id is passed
     */
    @Test
    public void getValidMessageObjectById() {
        MessageObject messageObject = StaticData.getInstance().getMessageObjects().get(0);
        assertNull("Message Object is null", Utilities.getMessageInfoById(messageObject.getMessageId()));
        assertEquals("Coud not getting valid data for %d", messageObject.getMessageId(),
                Utilities.getMessageInfoById(messageObject.getMessageId()).getMessageId(), messageObject.getMessageId());
    }

}