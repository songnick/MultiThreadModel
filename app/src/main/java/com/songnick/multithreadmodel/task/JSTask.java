package com.songnick.multithreadmodel.task;

import android.util.Log;

import com.songnick.multithreadmodel.data.DOMData;
import com.songnick.multithreadmodel.data.IOData;

public class JSTask implements ITask<DOMData>{

    private static final String TAG = "CommonTask";
    private IOData data = null;
    private DOMData result = null;

    public JSTask(IOData data){
        this.data = data;
    }

    @Override
    public void run() {
        Log.i(TAG, " js parse result " + data);
        //parse data
        result = new DOMData();
    }

    @Override
    public DOMData getResult() {
        return result;
    }
}
