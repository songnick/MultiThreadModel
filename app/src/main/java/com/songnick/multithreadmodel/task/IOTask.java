package com.songnick.multithreadmodel.task;

import android.util.Log;

import com.songnick.multithreadmodel.data.UIData;


/***
 * 根据URL解析UI渲染数据
 * */
public class IOTask implements ITask<UIData>{
    private static String TAG = "IOTask";
    private String url = null;

    private UIData result = null;

    public IOTask(String url){
       this.url = url;
    }

    @Override
    public void run() {
        Log.i(TAG, " load url: " + url);
        //parse ui data
        result = new UIData();
    }

    @Override
    public UIData getResult() {
        return result;
    }
}
