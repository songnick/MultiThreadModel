package com.songnick.multithreadmodel.multi_thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.songnick.multithreadmodel.WebView;
import com.songnick.multithreadmodel.task.ITask;

/***
 * UI渲染模拟类，接收UI处理的处理
 * */
public class RenderHandler extends BaseHandler {
    private static final String TAG = "RenderHandler";

    public RenderHandler(String name) {
        super(name);
    }

    public RenderHandler(Handler main){
        super(main,TAG);
    }

    @Override
    public void runTask(ITask task) {
        Message msg = new Message();
        msg.obj = task;
        mMyHandler.sendMessage(msg);
    }

    @Override
    protected Handler.Callback getCallback() {

        return message -> {
            if (message.obj instanceof ITask){
                ITask task = (ITask) message.obj;
                task.run();
                Message msg = new Message();
                msg.what = WebView.MSG_MAIN;
                msg.obj = task.getResult();
                mMain.sendMessage(msg);
            }
            return true;
        };
    }
}
