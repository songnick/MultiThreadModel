package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.data.DOMData;
import com.songnick.multithreadmodel.data.IOData;
import com.songnick.multithreadmodel.task.JSTask;
import com.songnick.multithreadmodel.task.IOTask;
import com.songnick.multithreadmodel.task.ITask;
import com.songnick.multithreadmodel.task.UITask;

/***
 * web view 模拟包装类
 * 保持
 * */
public class WebView {

    public final static int MSG_MAIN = 0x00;
    public final static int MSG_UI = 0x01;
    public final static int MSG_IO = 0x02;
    public final static int MSG_JS = 0x03;

    private static final String TAG = "WebView";

    private HandlerThread mainHandlerThread = new HandlerThread("Main_thread");
    private IHand ioHandler = null;
    private IHand renderHandler = null;
    private IHand jsHandler = null;

    private Handler mainHandler = null;

    private Handler.Callback mainCallback = message -> {
        switch (message.what){
            case MSG_IO:
                Log.i(TAG, "MSG_IO");
                if (ioHandler == null){
                    ioHandler = new IOHandler(mainHandler);
                }
                Log.i(TAG, " current handler: " + ioHandler.myHandler());
                ioHandler.myHandler().sendMessage(getTaskMessage(new IOTask((String) message.obj)));
                break;
            case MSG_UI:
                Log.i(TAG, "MSG_UI");
                if(renderHandler == null){
                    renderHandler = new RenderHandler(mainHandler);
                }
                renderHandler.myHandler().sendMessage(getTaskMessage(new UITask((DOMData) message.obj)));
                break;
            case MSG_JS:
                Log.i(TAG, " MSG_DEFAULT");
                if (jsHandler == null){
                    jsHandler = new JSHandler(mainHandler);
                }
                jsHandler.myHandler().sendMessage(getTaskMessage(new JSTask((IOData) message.obj)));
                break;
            case MSG_MAIN:
                Log.i(TAG, "loadWebView Success!");
                break;
            default:
                break;
        }
        return false;
    };

    private  Message getTaskMessage(ITask task){
        Message msg = new Message();
        msg.obj = task;
        return msg;
    }


    public WebView(){
        mainHandlerThread.start();
        mainHandler = new Handler(mainHandlerThread.getLooper(), mainCallback);
    }

    /***
     * 根据URL加载网页
     * @param url 网页地址(已经做好非法验证)
     * */
    public void loadWebView(String url){
        Message msg = new Message();
        msg.what = MSG_IO;
        msg.obj = url;
        mainHandler.sendMessage(msg);
    }
}
