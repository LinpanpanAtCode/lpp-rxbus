package com.lpp.pprxbus.rxbus;

import com.lpp.pprxbus.base.inter.IPostBus;
import com.lpp.pprxbus.rxbus.inter.IRxBusRegister;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

/**
 * RxBus 抽象类
 */
public abstract class RxBus {

    /**
     * 获取注册器
     * @return
     */
    public abstract IRxBusRegister getRxRegister();

    /**
     * 获取事件发送器
     * @return
     */
    public abstract IPostBus getRxPoster();

    /**
     * 获取rxbus里面的可观察者
     * @return
     */
    public abstract Observable toObservable();

    /**
     * 获取rxbus里面的可观者对应的flowable
     * @return
     */
    public abstract Flowable toFlowable();
}
