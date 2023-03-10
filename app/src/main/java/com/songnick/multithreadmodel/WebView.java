package com.songnick.multithreadmodel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.data.DOMData;
import com.songnick.multithreadmodel.data.IOData;
import com.songnick.multithreadmodel.multi_thread.IHand;
import com.songnick.multithreadmodel.multi_thread.IOHandler;
import com.songnick.multithreadmodel.multi_thread.JSHandler;
import com.songnick.multithreadmodel.multi_thread.RenderHandler;
import com.songnick.multithreadmodel.task.JSTask;
import com.songnick.multithreadmodel.task.IOTask;
import com.songnick.multithreadmodel.task.RenderTask;

/***
 * web view 模拟包装类
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
                if (message.obj instanceof String){
                    ioHandler.runTask(new IOTask((String) message.obj));
                }

                break;
            case MSG_UI:
                Log.i(TAG, "MSG_UI");
                if(renderHandler == null){
                    renderHandler = new RenderHandler(mainHandler);
                }
                if (message.obj instanceof DOMData){
                    renderHandler.runTask(new RenderTask((DOMData) message.obj));
                }
                break;
            case MSG_JS:
                Log.i(TAG, " MSG_JS");
                if (jsHandler == null){
                    jsHandler = new JSHandler(mainHandler);
                }
                if(message.obj instanceof IOData){
                    jsHandler.runTask(new JSTask((IOData) message.obj));
                }
                break;
            case MSG_MAIN:
                Log.i(TAG, "loadWebView Success!");
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
