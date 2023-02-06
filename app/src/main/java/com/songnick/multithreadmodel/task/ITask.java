package com.songnick.multithreadmodel.task;

/**
 * task接口定义
 * **/
public interface ITask<T> extends Runnable {

    T getResult();

}
