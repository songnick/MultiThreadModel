package com.songnick.multithreadmodel.task;

import android.util.Log;

public class IOTask implements Runnable{

    private static String TAG = "IOTask";

    private String url = null;

    public IOTask(String url){
       this.url = url;
    }

    @Override
    public void run() {
        Log.i(TAG, " load url: " + url);
    }
}
