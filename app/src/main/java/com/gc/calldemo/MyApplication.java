package com.gc.calldemo;

import android.app.Application;

import com.gc.call.CallManage;
import com.gc.module.CallTarget1;
import com.gc.module.CallTarget2;
import com.gc.module.CallTarget3;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CallManage.getInstance().addTarget(new CallTarget1());
        CallManage.getInstance().addTarget(new CallTarget2());
        CallManage.getInstance().addTarget(new CallTarget3());
    }
}
