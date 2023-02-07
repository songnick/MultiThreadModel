package com.songnick.multithreadmodel.multi_thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.WebView;
import com.songnick.multithreadmodel.multi_thread.IHand;
import com.songnick.multithreadmodel.task.ITask;
import com.songnick.multithreadmodel.task.JSTask;

/****
 * js 解析模拟类，处理数据解析
 * */

public class JSHandler extends BaseHandler {
    private static final String TAG = "JSHandler";

    public JSHandler(Handler main){
        super(main, TAG);
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
                msg.what = WebView.MSG_UI;
                msg.obj = task.getResult();
                mMain.sendMessage(msg);
            }
            return true;
        };
    }
}
