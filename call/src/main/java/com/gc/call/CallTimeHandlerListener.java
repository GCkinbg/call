package com.gc.call;


public interface CallTimeHandlerListener {

    //接受请求有不能立即处理的，处理需要耗时的回调方法
    public void timeHandler(CallReturnBean bean);
}
