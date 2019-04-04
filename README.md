# call
轻量化组件化通信工具
1.0.0版本功能
modeule与modeule之间的通信
发起通信后，直接返回
发起通信后，耗时返回
在主线程运行
在非主线程运行
在非主线程运行，但在主线程返回数据



在Application的onCreate方法中初始化CallManage，并且把对应的CallTarget添加进入队列,调用addTarget方法时要保证getTag返回的字符串不能重复
public class MyApplication extends Application {

    @Override
    public void onCreate() {
    super.onCreate();
    //添加CallTarget
    CallManage.getInstance().addTarget(new CallTarget1());
    }
}


//发起呼叫
//新建发起呼叫的bean，并设置目标tag，如果发起时没有找到目标，则返回 没有找到对应的CallTarget
CallParticipationBean bean=new CallParticipationBean("CallTarget3");
//添加入参
bean.addParameter("data","入参");
//设置Context,获取的时候做判null，不保证一定有
bean.setContext(this);     
//设置对应线程信息，
//CallConstant.LOOPER_MAIN=0x0101; //主线程处理
//CallConstant.LOOPER_NO_MAIN=0x0102; //不在主线程处理,
//CallConstant.LOOPER_NO_MAIN_HANDLER_AND_MIAN_RETURN=0x0103; //不在主线程处理，在主线程回调
bean.setLooper(CallConstant.LOOPER_NO_MAIN);
//设置接受返回对象事件，可不设置
bean.setCallReturnListener(new CallReturnListener() {
    @Override
    public void onReturn(CallReturnBean bean) {
      //处理返回对象，非必须返回
    }
});
//发起呼叫
CallManage.getInstance().handleTarget(bean);


//新建耗时返回的CallTarget
public class CallTarget1 extends CallTimeHandTarget {

    @Override
    public void delayProcessing(CallParticipationBean bean, final CallTimeHandlerListener listener) {
        //处理数据
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
         //返回处理结果
        listener.timeHandler(returnBean);
    }

    @Override
    public String getTag() {
        return "CallTarget1";//设置tag
    }
}

//新建直接返回的CallTarget
public class CallTarget2 extends CallImmediatelyHandlerTarget {

    @Override
    public String getTag() {
        return "CallTarget2";//设置tag
    }

    @Override
    public CallReturnBean onHandle(CallParticipationBean bean) {
        //处理数据
        Log.i("CallManage", "CallTarget1  Looper=" + Looper.myLooper());
        CallReturnBean returnBean = new CallReturnBean();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", "调用成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        returnBean.setReturnJson(jsonObject.toString());
        //返回处理结果
        return returnBean;
    }
}

作者
QQ:2190151331
有任何建议请QQ联系
