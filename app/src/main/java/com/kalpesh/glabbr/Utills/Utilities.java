package com.kalpesh.glabbr.Utills;

import com.kalpesh.glabbr.models.MessageObject;
import com.kalpesh.glabbr.models.MessageStatus;
import com.kalpesh.glabbr.models.StaticData;
import com.kalpesh.glabbr.models.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utilities {

    public static String timeString(long millis, Locale locale) {
        return dateForFormat("hh:mm a", millis, locale);
    }

    public static String dateFormatMonthAndTime(long millis, Locale locale) {
        return dateForFormat("MMMM dd, hh:mm a", millis, locale);
    }

    public static String dateForFormat(String formatStr, long seconds, Locale locale) {
        Calendar cal = new GregorianCalendar(TimeZone.getDefault());
        cal.setTimeInMillis(seconds);
        SimpleDateFormat format = new SimpleDateFormat(formatStr, locale != null ? locale : Locale.getDefault());
        format.setCalendar(cal);
        format.setTimeZone(TimeZone.getDefault());
        return format.format(cal.getTime());
    }

    public static User getUserById(int id) {
            for(User user : StaticData.getInstance().getUsers())   {
                if(user.getId() == id) {
                    return user;
                }
            }
        return null;
    }

    public static MessageObject getMessageInfoById(int id) {
        for(MessageObject messageObject : StaticData.getInstance().getMessageObjects())   {
            if(messageObject.getMessageId() == id) {
                return messageObject;
            }
        }
        return null;
    }
}
