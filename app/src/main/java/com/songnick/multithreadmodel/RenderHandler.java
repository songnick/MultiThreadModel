package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.task.ITask;
import com.songnick.multithreadmodel.task.UITask;

public class RenderHandler extends HandlerThread implements IHand {
    private static final String TAG = "UIHandler";

    private Handler main = null;
    private Handler myHandler = null;
    private Handler.Callback callback = message -> {
        Log.i(TAG, " handle message");
        UITask task = (UITask) message.obj;
        task.run();
        Message msg = new Message();
        msg.what = WebView.MSG_MAIN;
        msg.obj = task.getResult();
        main.sendMessage(msg);
        return true;
    };

    public RenderHandler(String name) {
        super(name);
    }

    public RenderHandler(Handler main){
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
