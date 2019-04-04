package com.gc.call;

//接受请求有不能立即处理的，处理需要耗时的继承CallImmediatelyHandlerTarget
public interface CallTimeHandlerListener {

    public void timeHandler(CallReturnBean bean);
}
