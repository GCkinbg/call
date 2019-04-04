package com.gc.calldemo;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.gc.call.CallConstant;
import com.gc.call.CallManage;
import com.gc.call.CallParticipationBean;
import com.gc.call.CallReturnBean;
import com.gc.call.CallReturnListener;

public class MainActivity extends AppCompatActivity {

    private String TAG="CallManage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCallTimeHandTarget();
        setImmediatelyHandlerTarget();
        findViewById(R.id.tv7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget3");
                bean.addParameter("data","入参");
                bean.setContext(MainActivity.this);
                CallManage.getInstance().handleTarget(bean);
            }
        });
    }

    private void setImmediatelyHandlerTarget() {
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget2");
                bean.setCallReturnListener(new CallReturnListener() {
                    @Override
                    public void onReturn(CallReturnBean bean) {
                        Log.i(TAG,"Looper="+ Looper.myLooper()+
                                "  CallReturnBean="+ JSON.toJSONString(bean));
                    }
                });
                CallManage.getInstance().handleTarget(bean);
            }
        });

        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget2");
                bean.setLooper(CallConstant.LOOPER_NO_MAIN);
                bean.setCallReturnListener(new CallReturnListener() {
                    @Override
                    public void onReturn(CallReturnBean bean) {
                        Log.i(TAG,"Looper="+ Looper.myLooper()+
                                "  CallReturnBean="+ JSON.toJSONString(bean));
                    }
                });
                CallManage.getInstance().handleTarget(bean);
            }
        });
        findViewById(R.id.tv6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget2");
                bean.setLooper(CallConstant.LOOPER_NO_MAIN_HANDLER_AND_MIAN_RETURN);
                bean.setCallReturnListener(new CallReturnListener() {
                    @Override
                    public void onReturn(CallReturnBean bean) {
                        Log.i(TAG,"Looper="+ Looper.myLooper()+
                                "  CallReturnBean="+ JSON.toJSONString(bean));
                    }
                });
                CallManage.getInstance().handleTarget(bean);
            }
        });
    }


    private void setCallTimeHandTarget() {
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget1");
                bean.setCallReturnListener(new CallReturnListener() {
                    @Override
                    public void onReturn(CallReturnBean bean) {
                        Log.i(TAG,"Looper="+ Looper.myLooper()+
                                "  CallReturnBean="+ JSON.toJSONString(bean));
                    }
                });
                CallManage.getInstance().handleTarget(bean);
            }
        });

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget1");
                bean.setLooper(CallConstant.LOOPER_NO_MAIN);
                bean.setCallReturnListener(new CallReturnListener() {
                    @Override
                    public void onReturn(CallReturnBean bean) {
                        Log.i(TAG,"Looper="+ Looper.myLooper()+
                                "  CallReturnBean="+ JSON.toJSONString(bean));
                    }
                });
                CallManage.getInstance().handleTarget(bean);
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallParticipationBean bean=new CallParticipationBean("CallTarget1");
                bean.setLooper(CallConstant.LOOPER_NO_MAIN_HANDLER_AND_MIAN_RETURN);
                bean.setCallReturnListener(new CallReturnListener() {
                    @Override
                    public void onReturn(CallReturnBean bean) {
                        Log.i(TAG,"Looper="+ Looper.myLooper()+
                                "  CallReturnBean="+ JSON.toJSONString(bean));
                    }
                });
                CallManage.getInstance().handleTarget(bean);
            }
        });
    }
}
