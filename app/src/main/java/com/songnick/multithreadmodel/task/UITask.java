package com.songnick.multithreadmodel.task;

import android.util.Log;

import com.songnick.multithreadmodel.data.UIData;

/**
 * 根据UI渲染数据渲染整个UI效果并返回是否渲染成功
 * */
public class UITask implements ITask<String>{

    private static String TAG = "UITask";

    private UIData ui = null;
    private String result = null;

    public UITask(UIData ui){
        this.ui = ui;
    }

    @Override
    public void run() {
        Log.i(TAG, " update ui: " + ui);
        result = "success";
    }

    @Override
    public String getResult() {
        return result;
    }
}
