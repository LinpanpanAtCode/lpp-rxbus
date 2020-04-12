package com.lpp.pprxbus.base;

import androidx.annotation.NonNull;

import com.lpp.pprxbus.builder.RxBusEventBuilder;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 基于黏性的事件流
 * {@link BaseBus}
 * 在BaseBus的基础上
 *
 * @author linpanpan
 * @date 2020/4/11
 */
public abstract class BaseStickBus extends BaseBus {
    /**
     * 用于保存黏性事件流
     */
    protected ConcurrentHashMap<Object,Object> mStickEvents;

    /**
     * 发送一个黏性的事件流
     * @param stickObject
     * @return
     */
    public abstract void postStick(@NonNull Object stickObject);

    /**
     * 注册一个黏性事件
     * @param consumer
     */
    public abstract Disposable registerStickEvent(@NonNull Consumer consumer);

    /**
     * 注册一个黏性事件，并且设置接收的事件流所在的线程
     * @param consumer
     * @param subscribeOnWhichScheduler {@link Schedulers},{@link io.reactivex.rxjava3.android.schedulers.AndroidSchedulers}
     * @return
     */
    public abstract Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, Scheduler subscribeOnWhichScheduler);

    /**
     * 注册一个带失败回调的黏性事件流
     * @param consumer
     * @param error
     * @return
     */
    public abstract Disposable registerStickEvent(@NonNull Consumer consumer, @NonNull Consumer error);

    /**
     * 注册一个带失败回调的黏性事件，并且设置接收的事件流所在的线程
     * @param consumer
     * @param error
     * @param subscribeOnWhichScheduler {@link Schedulers},{@link io.reactivex.rxjava3.android.schedulers.AndroidSchedulers}
     * @return
     */
    public abstract Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, Scheduler subscribeOnWhichScheduler);

    /**
     * 注册一个完成的黏性事件
     * @param consumer
     * @param error
     * @param complete
     * @return
     */
    public abstract Disposable registerStickEvent(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete);

    /**
     * 注册一个带完整的黏性事件流，并且设置接收的事件流所在的线程
     * @param consumer
     * @param error
     * @param complete
     * @param subscribeOnWhichScheduler {@link Schedulers},{@link io.reactivex.rxjava3.android.schedulers.AndroidSchedulers}
     * @return
     */
    public abstract Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete,Scheduler subscribeOnWhichScheduler);

    /**
     * 取消该黏性的事件流
     */
    public abstract void removeStickEvent(@NonNull Object stickEvent);

    /**
     * 清楚所有的黏性事件流
     */
    public abstract void clearAllStickEvents();

    /**
     * 使用构建者注册一个eventBus
     * @param eventBuilder
     */
    public abstract Disposable registerEvent(RxBusEventBuilder eventBuilder);
}
