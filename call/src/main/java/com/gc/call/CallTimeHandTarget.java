package com.gc.call;

//接受请求有不能立即处理的，处理需要耗时的继承CallImmediatelyHandlerTarget
public abstract class CallTimeHandTarget extends CallTarget {

    // 耗时处理数据
    public abstract void delayProcessing(CallParticipationBean bean, CallTimeHandlerListener listener);

}
