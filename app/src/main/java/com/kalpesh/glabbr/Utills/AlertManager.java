package com.kalpesh.glabbr.Utills;

import android.content.Context;
import android.widget.Toast;

public class AlertManager {
    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
