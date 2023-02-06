package com.songnick.multithreadmodel.task;

import android.util.Log;

import com.songnick.multithreadmodel.data.IOData;


/***
 * 根据URL解析UI渲染数据
 * */
public class IOTask implements ITask<IOData>{
    private static String TAG = "IOTask";
    private String url = null;

    private IOData result = null;

    public IOTask(String url){
       this.url = url;
    }

    @Override
    public void run() {
        Log.i(TAG, " load url: " + url);
        //parse ui data
        result = new IOData();
    }

    @Override
    public IOData getResult() {
        return result;
    }
}
