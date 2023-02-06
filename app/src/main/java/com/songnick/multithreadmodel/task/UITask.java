package com.songnick.multithreadmodel.task;

import android.util.Log;

public class UITask implements Runnable{

    private static String TAG = "UITask";

    private String ui = null;
    public UITask(String ui){
        this.ui = ui;
    }

    @Override
    public void run() {
        Log.i(TAG, " run ui: " + ui);
    }
}
