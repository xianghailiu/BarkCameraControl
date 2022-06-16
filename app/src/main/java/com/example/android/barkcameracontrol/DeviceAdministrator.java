package com.example.android.barkcameracontrol;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.android.barkcameracontrol.util.Utils;

public class DeviceAdministrator extends DeviceAdminReceiver {

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        Utils.showToastShort("Device Admin has been disabled.");
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Utils.showToastShort("Device Admin has been enabled.");
    }
}
