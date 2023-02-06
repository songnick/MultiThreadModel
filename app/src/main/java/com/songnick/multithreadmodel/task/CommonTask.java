package com.songnick.multithreadmodel.task;

import android.util.Log;

public class CommonTask implements ITask<String>{

    private static final String TAG = "CommonTask";
    private String common = null;
    private String result = null;

    public CommonTask(String common){
        this.common = common;
    }

    @Override
    public void run() {
        Log.i(TAG, " run common " + common);
        result = "success";
    }

    @Override
    public String getResult() {
        return result;
    }
}
