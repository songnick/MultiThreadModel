package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class IOHandler extends HandlerThread implements Runnable,IHand {
    private static final String TAG = "IOHandler";

    private Handler main = null;
    private Handler myHandler = null;
    private Handler.Callback callback = message -> {
        Log.i(TAG, " handle message");
        message.getCallback().run();
        main.sendEmptyMessage(WebView.MSG_UI);
        return true;
    };

    public IOHandler(String name) {
        super(name);
    }

    public IOHandler(Handler main){
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
