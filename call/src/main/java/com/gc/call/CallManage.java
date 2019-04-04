package com.gc.call;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class CallManage {

    private String TAG = "CallManage";

    private Map<String, CallTarget> map = new HashMap<>();
    private Handler handler = new Handler(Looper.getMainLooper());

    private static CallManage callManage;

    public static CallManage getInstance() {
        if (callManage == null) {
            callManage = new CallManage();
        }
        return callManage;
    }

    public void addTarget(CallTarget callTarget) {
        map.put(callTarget.getTag(), callTarget);
    }

    //处理call
    public void handleTarget(final CallParticipationBean bean) {
        Log.i(TAG, "handleTarget:" + bean.getTag());
        CallTarget callTarget = map.get(bean.getTag());
        if (callTarget == null) {
            if (bean.getListener() != null) {
                CallReturnBean callReturn = new CallReturnBean();
                callReturn.setMsg("没有找到对应的CallTarget");
                callReturn.setCode(CallConstant.CODE_NO_TARGET);
                bean.getListener().onReturn(callReturn);
            }
        } else {
            if (callTarget instanceof CallImmediatelyHandlerTarget) {
                onImmediatelyHandler(bean, (CallImmediatelyHandlerTarget) callTarget);
            } else if (callTarget instanceof CallTimeHandTarget) {
                onTimeHandHandler(bean, (CallTimeHandTarget) callTarget);
            }
        }
    }

    //处理耗时不能立刻返回的call
    private void onTimeHandHandler(final CallParticipationBean bean, final CallTimeHandTarget target) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (bean.getLooper() == CallConstant.LOOPER_MAIN) {
                    target.delayProcessing(bean, new CallTimeHandlerListener() {
                        @Override
                        public void timeHandler(CallReturnBean c) {
                            if (bean.getListener() != null) {
                                bean.getListener().onReturn(c);
                            }
                        }
                    });
                } else if (bean.getLooper() == CallConstant.LOOPER_NO_MAIN) {
                    AsyncTask asyncTask = new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            target.delayProcessing(bean, new CallTimeHandlerListener() {
                                @Override
                                public void timeHandler(CallReturnBean c) {
                                    if (bean.getListener() != null) {
                                        bean.getListener().onReturn(c);
                                    }
                                }
                            });
                            return null;
                        }
                    };
                    asyncTask.execute();
                } else if (bean.getLooper() == CallConstant.LOOPER_NO_MAIN_HANDLER_AND_MIAN_RETURN) {
                    AsyncTask asyncTask = new AsyncTask() {

                        @Override
                        protected CallReturnBean doInBackground(Object[] objects) {
                            target.delayProcessing(bean, new CallTimeHandlerListener() {
                                @Override
                                public void timeHandler(final CallReturnBean c) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (bean.getListener() != null) {
                                                bean.getListener().onReturn(c);
                                            }
                                        }
                                    });
                                }
                            });
                            return null;
                        }

                    };
                    asyncTask.execute();
                }
            }
        });
    }


    //处理不耗时能立刻返回的call
    private void onImmediatelyHandler(final CallParticipationBean bean,
                                      final CallImmediatelyHandlerTarget target) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (bean.getLooper() == CallConstant.LOOPER_MAIN) {
                    CallReturnBean c = target.onHandle(bean);
                    if (bean.getListener() != null) {
                        bean.getListener().onReturn(c);
                    }
                } else if (bean.getLooper() == CallConstant.LOOPER_NO_MAIN) {
                    AsyncTask asyncTask = new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            CallReturnBean c = target.onHandle(bean);
                            if (bean.getListener() != null) {
                                bean.getListener().onReturn(c);
                            }
                            return null;
                        }
                    };
                    asyncTask.execute();
                } else if (bean.getLooper() == CallConstant.LOOPER_NO_MAIN_HANDLER_AND_MIAN_RETURN) {
                    AsyncTask asyncTask = new AsyncTask() {

                        @Override
                        protected CallReturnBean doInBackground(Object[] objects) {
                            return target.onHandle(bean);
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            super.onPostExecute(o);
                            if (bean.getListener() != null) {
                                bean.getListener().onReturn((CallReturnBean) o);
                            }
                        }
                    };
                    asyncTask.execute();
                }
            }
        });
    }


}
