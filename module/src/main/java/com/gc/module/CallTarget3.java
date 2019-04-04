package com.gc.module;

import android.content.Context;
import android.content.Intent;

import com.gc.call.CallImmediatelyHandlerTarget;
import com.gc.call.CallParticipationBean;
import com.gc.call.CallReturnBean;


public class CallTarget3 extends CallImmediatelyHandlerTarget {


    @Override
    public String getTag() {
        return "CallTarget3";
    }

    @Override
    public CallReturnBean onHandle(CallParticipationBean bean) {
        Context context = bean.getContext();
        if (context != null) {
            context.startActivity(new Intent(context, MainActivity.class));
        }
        return null;
    }
}
