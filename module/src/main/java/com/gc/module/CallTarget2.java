package com.gc.module;

import android.os.Looper;
import android.util.Log;

import com.gc.call.CallImmediatelyHandlerTarget;
import com.gc.call.CallParticipationBean;
import com.gc.call.CallReturnBean;

import org.json.JSONException;
import org.json.JSONObject;


public class CallTarget2 extends CallImmediatelyHandlerTarget {

    @Override
    public String getTag() {
        return "CallTarget2";
    }

    @Override
    public CallReturnBean onHandle(CallParticipationBean bean) {
        Log.i("CallManage", "CallTarget1  Looper=" + Looper.myLooper());
        CallReturnBean returnBean = new CallReturnBean();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", "调用成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        returnBean.setReturnJson(jsonObject.toString());
        return returnBean;
    }
}
