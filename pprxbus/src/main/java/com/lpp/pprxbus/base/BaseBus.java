package com.lpp.pprxbus.base;

import androidx.annotation.NonNull;

import com.lpp.pprxbus.RxBus;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * 定义基于事件总线的抽象基类
 * @author linpanpan
 * @date 2020/4/11
 */
public abstract class BaseBus {
    private volatile static RxBus mRxBus;
    /**
     * 注册一个事件
     * 默认的响应事件发生在订阅的同一线程线
     * @param consumer
     * @return
     */
    public abstract Disposable registerEvent(@NonNull Consumer consumer) ;

    /**
     * 注册一个事件
     * 并且指定响应事件的发生的所在线程
     * @param consumer
     * @param subscribeOnWhichScheduler {@link io.reactivex.rxjava3.schedulers.Schedulers}
     * @return
     */
    public abstract Disposable registerEventWithScheduler(@NonNull Consumer consumer , Scheduler subscribeOnWhichScheduler) ;
    /**
     * 注册一个事件，带error的事件
     * @param consumer
     * @param error
     * @return
     */
    public abstract Disposable registerEvent(@NonNull Consumer consumer,@NonNull Consumer error) ;

    /**
     * 注册一个事件，带error的事件
     * 并且指定响应事件的发生的所在线程
     * @param consumer
     * @param error
     * @param subscribeOnWhichScheduler {@link io.reactivex.rxjava3.schedulers.Schedulers}
     * @return
     */
    public abstract Disposable registerEventWithScheduler(@NonNull Consumer consumer,@NonNull Consumer error,Scheduler subscribeOnWhichScheduler) ;


    /**
     * 注册一个完整的事件
     * @param consumer
     * @param error
     * @param complete
     * @return
     */
    public abstract Disposable registerEvent(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete) ;

    /**
     * 注册一个完整的事件
     * @param consumer
     * @param error
     * @param complete
     * @param subscribeOnWhichScheduler {@link io.reactivex.rxjava3.schedulers.Schedulers}
     * @return
     */
    public abstract Disposable registerEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete,Scheduler subscribeOnWhichScheduler) ;

    /**
     * 反注册
     * @param subscriber
     */
    public abstract void unRegisterEvent(@NonNull Disposable subscriber);

    /**
     * 判断当前是否有订阅者
     * @return
     */
    public abstract boolean hasSubscribes();

    /**
     * 发送一个消息事件
     * @param object
     */
    public abstract void post(@NonNull Object object);

    /**
     * 获取RxBus实例自己进行RxJava操作
     * 将当前的RxBus的队列转换成Obserable
     * @return
     */
    public abstract Observable toObservable();

    /**
     * 获取RxBus实例自己进行RxJava操作
     * 将当前的RxBus的队列转换成Flowable
     * @return
     */
    public abstract Flowable toFlowable();
}
