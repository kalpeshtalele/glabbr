package com.kalpesh.glabbr.Utills;

import android.content.Context;
import android.graphics.Typeface;

public class Typefaces {
    public enum Style {
        BOLD, ITALIC, SEMI_BOLD_ITALIC, DANCING_REGULAR
    }

    private static Typefaces instance;
    private static final String TYPEFACE_BOLD_FILE_PATH = "fonts/OpenSans-Bold.ttf";
    private static final String TYPEFACE_ITALIC_FILE_PATH = "fonts/OpenSans-Italic.ttf";
    private static final String TYPEFACE_SEMI_BOLD_ITALIC_FILE_PATH = "fonts/OpenSans-SemiboldItalic.ttf";
    private static final String TYPEFACE_DANCING_SCRIPT = "fonts/DancingScript.ttf";

    private Typeface bold;
    private Typeface italic;
    private Typeface semiboldItalic;
    private Typeface dancingScript;

    private Typefaces(Context context) {
        bold = Typeface.createFromAsset(context.getAssets(), TYPEFACE_BOLD_FILE_PATH);
        italic = Typeface.createFromAsset(context.getAssets(), TYPEFACE_ITALIC_FILE_PATH);
        semiboldItalic = Typeface.createFromAsset(context.getAssets(), TYPEFACE_SEMI_BOLD_ITALIC_FILE_PATH);
        dancingScript = Typeface.createFromAsset(context.getAssets(), TYPEFACE_DANCING_SCRIPT);
    }

    public static Typefaces getInstance(Context context) {
        if (instance == null) {
            instance = new Typefaces(context);
        }
        return instance;
    }

    public Typeface getTypeface(Style style) {
        switch (style) {
            case BOLD:
                return bold;
            case ITALIC:
                return italic;
            case SEMI_BOLD_ITALIC:
                return semiboldItalic;
            case DANCING_REGULAR:
                return dancingScript;
            default:
                return dancingScript;
        }
    }
}
