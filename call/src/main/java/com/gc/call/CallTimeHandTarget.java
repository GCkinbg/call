package com.gc.call;

public abstract class CallTimeHandTarget extends CallTarget {

    // 耗时处理数据
    public abstract void delayProcessing(CallParticipationBean bean, CallTimeHandlerListener listener);

}
