package com.gc.call;

public class CallConstant {

    public static int CODE_SUCCESS= 0x0001;; //返回成功
    public static int CODE_NO_TARGET =0x0002;//返回没有找到目标

    public static int LOOPER_MAIN=0x0101; //主线程处理
    public static int LOOPER_NO_MAIN=0x0102; //不在主线程处理,
    public static int LOOPER_NO_MAIN_HANDLER_AND_MIAN_RETURN=0x0103; //不在主线程处理，在主线程回调

}
