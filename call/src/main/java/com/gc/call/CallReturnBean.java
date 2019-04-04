package com.gc.call;

//call后的返回数据bean
public class CallReturnBean {
    private String msg; //错误提示
    private String returnJson;//返回结果
    private int code = CallConstant.CODE_SUCCESS;//返回 1：表示成功,默认值，2：没有找到目标  其他表示失败，可自定义

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
