package com.lpp.pprxbus.rxbus.inter;

import androidx.annotation.NonNull;

import com.lpp.pprxbus.base.ibuilder.IPostBuilder;
import com.lpp.pprxbus.base.inter.IRegisterBus;
import com.lpp.pprxbus.rxbus.build.IRxPostBuilder;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public interface IRxBusRegister
        <EVENT extends Consumer,ERROR extends Consumer,RESULT extends Disposable,THREADMODE extends Scheduler,COMPLETE extends Action>
        extends IRegisterBus<EVENT,RESULT> {
    /**
     * 注册一个事件
     * 并且指定响应事件的发生的所在线程
     * @param consumer
     * @param subscribeOnWhichScheduler 
     * @return
     */
    public RESULT registerEventWithScheduler(@NonNull EVENT consumer , THREADMODE subscribeOnWhichScheduler) ;
    /**
     * 注册一个事件，带error的事件
     * @param consumer
     * @param error
     * @return
     */
    public RESULT registerEvent(@NonNull EVENT consumer,@NonNull ERROR error) ;

    /**
     * 注册一个事件，带error的事件
     * 并且指定响应事件的发生的所在线程
     * @param consumer
     * @param error
     * @param subscribeOnWhichScheduler 
     * @return
     */
    public RESULT registerEventWithScheduler(@NonNull EVENT consumer,@NonNull ERROR error,THREADMODE subscribeOnWhichScheduler);


    /**
     * 注册一个完整的事件
     * @param consumer
     * @param error
     * @param complete
     * @return
     */
    public RESULT registerEvent(@NonNull EVENT consumer, @NonNull ERROR error, @NonNull COMPLETE complete) ;

    /**
     * 注册一个完整的事件
     * @param consumer
     * @param error
     * @param complete
     * @param subscribeOnWhichScheduler 
     * @return
     */
    public RESULT registerEventWithScheduler(@NonNull EVENT consumer, @NonNull ERROR error, @NonNull COMPLETE complete,THREADMODE subscribeOnWhichScheduler) ;

    /**
     * 反注册
     * @param subscriber
     */
    public void unRegisterEvent(@NonNull RESULT subscriber);

    /**
     * 判断当前是否有订阅者
     * @return
     */
    public boolean hasSubscribes();

    /**
     * 注册一个黏性事件
     * @param consumer
     */
    public RESULT registerStickEvent(@NonNull EVENT consumer);

    /**
     * 注册一个黏性事件，并且设置接收的事件流所在的线程
     * @param consumer
     * @param subscribeOnWhichScheduler 
     * @return
     */
    public RESULT registerStickEventWithScheduler(@NonNull EVENT consumer, THREADMODE subscribeOnWhichScheduler);

    /**
     * 注册一个带失败回调的黏性事件流
     * @param consumer
     * @param error
     * @return
     */
    public RESULT registerStickEvent(@NonNull EVENT consumer, @NonNull ERROR error);

    /**
     * 注册一个带失败回调的黏性事件，并且设置接收的事件流所在的线程
     * @param consumer
     * @param error
     * @param subscribeOnWhichScheduler 
     * @return
     */
    public RESULT registerStickEventWithScheduler(@NonNull EVENT consumer, @NonNull ERROR error, THREADMODE subscribeOnWhichScheduler);

    /**
     * 注册一个完成的黏性事件
     * @param consumer
     * @param error
     * @param complete
     * @return
     */
    public RESULT registerStickEvent(@NonNull EVENT consumer, @NonNull ERROR error, @NonNull COMPLETE complete);

    /**
     * 注册一个带完整的黏性事件流，并且设置接收的事件流所在的线程
     * @param consumer
     * @param error
     * @param complete
     * @param subscribeOnWhichScheduler 
     * @return
     */
    public RESULT registerStickEventWithScheduler(@NonNull EVENT consumer, @NonNull ERROR error, @NonNull COMPLETE complete,THREADMODE subscribeOnWhichScheduler);


    /**
     * 使用构建者注册一个eventBus
     * @param eventBuilder
     */
    public RESULT registerEvent(IRxPostBuilder eventBuilder);

    /**
     * 注册一个普通对象T，返回对应的注册结果
     * @param event
     * @return
     */
    public RESULT registerEvent(@NonNull EVENT event);


    /**
     * 注册一个IPostBuilder的消息
     * @param postBuilder
     */
    public RESULT registerEvent(@NonNull IPostBuilder postBuilder);

}
