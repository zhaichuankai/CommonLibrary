package com.enniu.commonlibrary.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
