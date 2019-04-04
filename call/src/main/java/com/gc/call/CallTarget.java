package com.gc.call;

abstract class CallTarget {

    /**
     * 获取目标 为了防止重复建议命名规范： module名+事件名 例 web_goWebPage
     * web是module名 goWebPage是跳转web页面，为事件名
     *
     * @return
     */
    public abstract String getTag();


}
