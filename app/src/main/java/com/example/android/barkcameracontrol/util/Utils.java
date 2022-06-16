package com.example.android.barkcameracontrol.util;

import android.widget.Toast;

import com.example.android.barkcameracontrol.BarkCameraControlApplicatioin;

public class Utils {
    public static void showToastShort(String text) {
        Toast.makeText(BarkCameraControlApplicatioin.getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
