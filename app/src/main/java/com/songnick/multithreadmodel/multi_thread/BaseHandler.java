package com.songnick.multithreadmodel.multi_thread;

import android.os.Handler;
import android.os.HandlerThread;

public abstract class BaseHandler extends HandlerThread implements IHand {

    /**主线程处理器**/
    protected Handler mMain;

    /**自有处理器*/
    protected Handler mMyHandler = null;


    public BaseHandler(String name) {
        super(name);
    }

    public BaseHandler(Handler main, String name){
        this(name);
        this.mMain = main;
        start();
        mMyHandler = new Handler(getLooper(), getCallback());
    }


    /**
     * 获取具体处理函数
     * @return
     * **/
    protected abstract Handler.Callback getCallback();

}
