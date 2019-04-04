package com.gc.call;

//接受请求有能立即处理的继承CallImmediatelyHandlerTarget
public abstract class CallImmediatelyHandlerTarget extends CallTarget {

    //处理数据
    public abstract CallReturnBean onHandle(CallParticipationBean bean);

}
