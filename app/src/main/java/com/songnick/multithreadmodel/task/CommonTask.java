package com.songnick.multithreadmodel.task;

import android.util.Log;

public class CommonTask implements Runnable{

    private static final String TAG = "CommonTask";
    private String common = null;

    public CommonTask(String common){
        this.common = common;
    }

    @Override
    public void run() {
        Log.i(TAG, " run common " + common);
    }
}
