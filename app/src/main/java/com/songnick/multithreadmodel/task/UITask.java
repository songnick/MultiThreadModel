package com.songnick.multithreadmodel.task;

import android.util.Log;

import com.songnick.multithreadmodel.data.DOMData;
import com.songnick.multithreadmodel.data.IOData;

/**
 * 根据UI渲染数据渲染整个UI效果并返回是否渲染成功
 * */
public class UITask implements ITask<String>{

    private static String TAG = "UITask";

    private DOMData data = null;
    private String result = null;

    public UITask(DOMData data){
        this.data = data;
    }

    @Override
    public void run() {
        Log.i(TAG, " update ui: " + data);
        result = "success";
    }

    @Override
    public String getResult() {
        return result;
    }
}
