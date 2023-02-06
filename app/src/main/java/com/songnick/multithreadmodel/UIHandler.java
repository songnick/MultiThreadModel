package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class UIHandler extends HandlerThread implements Runnable,IHand {
    private static final String TAG = "UIHandler";

    private Handler main = null;
    private Handler myHandler = null;
    private Handler.Callback callback = message -> {
        Log.i(TAG, " handle message");
        message.getCallback().run();
        main.sendEmptyMessage(WebView.MSG_MAIN);
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