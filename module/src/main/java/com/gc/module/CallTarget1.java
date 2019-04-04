package com.gc.module;

import android.os.Looper;
import android.util.Log;

import com.gc.call.CallParticipationBean;
import com.gc.call.CallReturnBean;
import com.gc.call.CallTimeHandTarget;
import com.gc.call.CallTimeHandlerListener;

import org.json.JSONException;
import org.json.JSONObject;


public class CallTarget1 extends CallTimeHandTarget {

    @Override
    public void delayProcessing(CallParticipationBean bean, final CallTimeHandlerListener listener) {
        Log.i("CallManage", "CallTarget1  Looper=" + Looper.myLooper());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CallReturnBean returnBean = new CallReturnBean();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", "调用成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        returnBean.setReturnJson(jsonObject.toString());
        listener.timeHandler(returnBean);
    }

    @Override
    public String getTag() {
        return "CallTarget1";
    }
}
