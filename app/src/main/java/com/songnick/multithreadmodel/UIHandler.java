package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.task.ITask;

public class UIHandler extends HandlerThread implements IHand {
    private static final String TAG = "UIHandler";

    private Handler main = null;
    private Handler myHandler = null;
    private Handler.Callback callback = message -> {
        Log.i(TAG, " handle message");
        ITask<String> task = (ITask<String>) message.obj;
        task.run();
        Message msg = new Message();
        msg.what = WebView.MSG_MAIN;
        msg.obj = task.getResult();
        main.sendMessage(msg);
        return true;
    };

    public UIHandler(String name) {
        super(name);
    }

    public UIHandler(Handler main){
        this(TAG);
        this.main = main;
        start();
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        myHandler = new Handler(getLooper(), callback);
    }

    @Override
    public Handler myHandler(){

        return myHandler;
    }
}
