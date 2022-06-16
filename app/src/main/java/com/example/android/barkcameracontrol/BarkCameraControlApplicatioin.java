package com.example.android.barkcameracontrol;

import android.app.Application;
import android.content.Context;

public class BarkCameraControlApplicatioin extends Application {
    private static Application instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static Application getBarkCameraControlApplication() {
        return instance;
    }
}
