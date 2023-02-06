package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.songnick.multithreadmodel.task.CommonTask;
import com.songnick.multithreadmodel.task.IOTask;
import com.songnick.multithreadmodel.task.UITask;

public class WebView {

    public final static int MSG_MAIN = 0x00;
    public final static int MSG_UI = 0x01;
    public final static int MSG_IO = 0x02;
    public final static int MSG_DEFAULT = 0x03;

    private static final String TAG = "MainVM";

    private HandlerThread mainHandlerThread = new HandlerThread("Main_thread");
    private IHand ioHandler = null;
    private IHand uiHandler = null;
    private IHand defaultHandler = null;

    private Handler mainHandler = null;

    private Handler.Callback mainCallback = message -> {
        switch (message.what){
            case MSG_IO:
                Log.i(TAG, "MSG_IO");
                if (ioHandler == null){
                    ioHandler = new IOHandler(mainHandler);
                }
                ioHandler.myHandler().post(new IOTask((String) message.obj));
                break;
            case MSG_UI:
                Log.i(TAG, "MSG_UI");
                if(uiHandler == null){
                    uiHandler = new UIHandler(mainHandler);
                }
                uiHandler.myHandler().post(new UITask((String) message.obj));
                break;
            case MSG_DEFAULT:
                Log.i(TAG, " MSG_DEFAULT");
                if (defaultHandler == null){
                    defaultHandler = new DefaultHandler(mainHandler);
                }
                defaultHandler.myHandler().post(new CommonTask((String) message.obj));
                break;
            case MSG_MAIN:
                Log.i(TAG, "MSG_MAIN");
                break;
            default:
                break;
        }
        return false;
    };

    public WebView(){
        mainHandlerThread.start();
        mainHandler = new Handler(mainHandlerThread.getLooper(), mainCallback);
    }

    public void loadWebView(String url){
        Message msg = new Message();
        msg.what = MSG_IO;
        msg.obj = url;
        mainHandler.sendMessage(msg);
    }
}
