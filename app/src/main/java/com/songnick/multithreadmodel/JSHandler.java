package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.task.JSTask;

public class JSHandler extends HandlerThread implements IHand {
    private static final String TAG = "DefaultHandler";

    private Handler main = null;
    private Handler myHandler = null;
    private Handler.Callback callback = message -> {
        Log.i(TAG, " handle message");
        JSTask task = (JSTask) message.obj;
        task.run();
        Message msg = new Message();
        msg.what = WebView.MSG_UI;
        msg.obj = task.getResult();
        main.sendMessage(msg);
        return true;
    };

    public JSHandler(String name) {
        super(name);
    }

    public JSHandler(Handler main){
        this(TAG);
        this.main = main;
        start();
        myHandler = new Handler(getLooper(), callback);
    }

    @Override
    public Handler myHandler(){

        return myHandler;
    }
}
