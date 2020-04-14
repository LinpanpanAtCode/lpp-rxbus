package com.lpp.pprxbus.rxbus.build;

import com.lpp.pprxbus.base.ibuilder.IPostBuilder;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

/**
 *
 * @param <EVENT>
 * @param <THREADMODE>
 * @param <ERROR>
 * @param <COMPLETE>
 */
public interface IRxPostBuilder<EVENT extends Consumer,THREADMODE extends Scheduler,ERROR extends Consumer,COMPLETE extends Action> extends IPostBuilder<EVENT,THREADMODE> {
    /**
     * 获取rxbus发送失败回调
     * @return
     */
    public ERROR getErrorDo();

    /**
     * 获取rxbus发送完成的回调
     * @return
     */
    public COMPLETE getCompleteDo();

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
