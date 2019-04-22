package com.example.ihubtechnologies.superdocnew.activities;

import android.app.Application;

/**
 * Created by vamsi on 06-05-2017 for android custom font article
 */

public class CustomFontApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "SERIF", "Montserrat-Light.otf");

    }
}
