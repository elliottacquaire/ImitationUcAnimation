package com.li.imitationuc;

import android.app.Application;

public class ImitationUCApplication extends Application {

    private static ImitationUCApplication instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
    }

    public static ImitationUCApplication getInstance(){
        return instance;
    }
}
