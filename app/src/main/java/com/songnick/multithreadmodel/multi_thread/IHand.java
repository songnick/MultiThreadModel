package com.songnick.multithreadmodel.multi_thread;


import android.os.Handler;

import com.songnick.multithreadmodel.task.ITask;

public interface IHand {
    void runTask(ITask task);
}
