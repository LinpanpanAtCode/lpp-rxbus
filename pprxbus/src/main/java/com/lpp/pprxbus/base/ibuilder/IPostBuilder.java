package com.lpp.pprxbus.base.ibuilder;

/**
 * 定义一个消息注册者的建造者
 * 其中，包含以下几点
 * <i>发送的事件</i>
 * <i>是否是黏性事件</i>
 * <i>线程模式</i>
 * <i>接收的消息类型</i>
 */
public interface IPostBuilder<EVENT,THREADMODE> {
    /**
     * 设置注册的事件对象
     * @return
     */
    public EVENT getEvent();

    /**
     * 设置是否是黏性事件
     * @return
     */
    public boolean isStick();

    /**
     * 获取线程的模式
     * @return
     */
    public THREADMODE getThreadMode();

    /**
     * 定义需要的消息类型
     * @return
     */
    public int getEventType();
}
