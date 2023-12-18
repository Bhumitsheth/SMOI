package com.iipl.smoi.Utils;

import android.app.Application;

public class AppScreenNavigation extends Application {
    private static boolean sFirstRun = false;

    public static boolean fetchFirstRun() {
        boolean old = sFirstRun;
        sFirstRun = false;
        return old;
    }

    //--called when app process is created--
    @Override
    public void onCreate() {
        super.onCreate();

        sFirstRun = true;
    }
}
