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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StaticDataUnitTest {


    @Test
    public void validUserList() {
        Assert.assertNull("User list is null", StaticData.getInstance().getUsers());
        assertFalse("User array list is empty", StaticData.getInstance().getUsers().isEmpty());
    }

    @Test
    public void getValidMessageObjectById() {
        Assert.assertNull("Message Object array list is empty", StaticData.getInstance().getMessageObjects());
        assertFalse("User array list is empty", StaticData.getInstance().getMessageObjects().isEmpty());
    }

}