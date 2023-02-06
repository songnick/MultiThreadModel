package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.task.IOTask;

/***
 * IO 处理线程
 *
 * */
public class IOHandler extends HandlerThread implements IHand {
    private static final String TAG = "IOHandler";

    private Handler main = null;
    private Handler myHandler = null;
    private Handler.Callback callback = message -> {
        Log.i(TAG, " handle message");
        IOTask task = (IOTask) message.obj;
        task.run();
        Message msg = new Message();
        msg.what = WebView.MSG_JS;
        msg.obj = task.getResult();
        main.sendMessage(msg);
        return true;
    };

    public IOHandler(String name) {
        super(name);
    }

    public IOHandler(Handler main){
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
