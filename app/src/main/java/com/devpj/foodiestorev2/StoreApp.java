package com.devpj.foodiestorev2;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

public class StoreApp extends Application
{

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
//         OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }


}