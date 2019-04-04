package com.gc.call;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

//call的入参
public class CallParticipationBean {

    private Context context;
    private String tag; //目标
    private JSONObject parameter; //入参
    private CallReturnListener listener; //回调
    private int looper=CallConstant.LOOPER_MAIN;//是否在主线处理,为true在主线程处理


    public int getLooper() {
        return looper;
    }

    public void setLooper(int looper) {
        this.looper = looper;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public CallReturnListener getListener() {
        return listener;
    }

    public void setListener(CallReturnListener listener) {
        this.listener = listener;
    }

    public JSONObject getParameter() {
        return parameter;
    }

    public void setParameter(JSONObject parameter) {
        this.parameter = parameter;
    }

    public void addParameter(String key,String value){
        if (parameter==null){
            parameter=new JSONObject();
        }
        try {
            parameter.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
