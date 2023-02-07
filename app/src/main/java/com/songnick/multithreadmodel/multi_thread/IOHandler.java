package com.songnick.multithreadmodel.multi_thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.songnick.multithreadmodel.WebView;
import com.songnick.multithreadmodel.multi_thread.IHand;
import com.songnick.multithreadmodel.task.IOTask;
import com.songnick.multithreadmodel.task.ITask;

/***
 * IO 处理线程模拟类，处理网络、文件等耗时操作
 * */
public class IOHandler extends BaseHandler {
    private static final String TAG = "IOHandler";

    public IOHandler(Handler main){
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
                msg.what = WebView.MSG_JS;
                msg.obj = task.getResult();
                mMain.sendMessage(msg);
            }
            return true;
        };
    }
}
