package com.lpp.pprxbus;


import android.util.Log;

import androidx.annotation.NonNull;

import com.lpp.pprxbus.base.BaseStickBus;
import com.lpp.pprxbus.builder.RxBusEventBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;

/**
 * RxBus事件总线
 *
 * @author linpanpan
 */
public class RxBus extends BaseStickBus {
    private static final String TAG = "pp-rxBus";
    private FlowableProcessor<Object> mBus;
    private static volatile RxBus sRxBus = null;

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    /**
     * 获取事件总线
     *
     * @return
     */
    public static RxBus getDefaultBus() {
        if (sRxBus == null) {
            synchronized (RxBus.class) {
                if (sRxBus == null) {
                    sRxBus = new RxBus();
                }
            }
        }
        return sRxBus;
    }

    @Override
    public Disposable registerEvent(@NonNull Consumer consumer) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(false)
                .setConsumer(consumer);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerEventWithScheduler(@NonNull Consumer consumer, Scheduler subscribeOnWhichScheduler) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(false)
                .setConsumer(consumer)
                .setSubscribeOnWhichScheduler(subscribeOnWhichScheduler);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerEvent(@NonNull Consumer consumer, @NonNull Consumer error) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(false)
                .setConsumer(consumer)
                .setError(error);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, Scheduler subscribeOnWhichScheduler) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(false)
                .setConsumer(consumer)
                .setError(error)
                .setSubscribeOnWhichScheduler(subscribeOnWhichScheduler);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerEvent(@NonNull Consumer consumer, @NonNull Consumer error, Action complete) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(false)
                .setConsumer(consumer)
                .setError(error)
                .setComplete(complete);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete, Scheduler subscribeOnWhichScheduler) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(false)
                .setConsumer(consumer)
                .setError(error)
                .setComplete(complete)
                .setSubscribeOnWhichScheduler(subscribeOnWhichScheduler);
        return registerEvent(eventBuilder);
    }

    @Override
    public void post(@NonNull Object object) {
        if (object == null) return;
        if (mBus == null) return;
        mBus.onNext(object);
    }

    @Override
    public Disposable registerStickEvent(@NonNull Consumer consumer) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(true)
                .setConsumer(consumer);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, Scheduler subscribeOnWhichScheduler) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(true)
                .setConsumer(consumer)
                .setSubscribeOnWhichScheduler(subscribeOnWhichScheduler);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerStickEvent(@NonNull Consumer consumer, @NonNull Consumer error) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(true)
                .setConsumer(consumer)
                .setError(error);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, Scheduler subscribeOnWhichScheduler) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(true)
                .setConsumer(consumer)
                .setError(error)
                .setSubscribeOnWhichScheduler(subscribeOnWhichScheduler);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerStickEvent(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setStick(true)
                .setConsumer(consumer)
                .setError(error)
                .setComplete(complete);
        return registerEvent(eventBuilder);
    }

    @Override
    public Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete, Scheduler subscribeOnWhichScheduler) {
        RxBusEventBuilder eventBuilder = RxBusEventBuilder.create()
                .setConsumer(consumer)
                .setError(error)
                .setComplete(complete)
                .setSubscribeOnWhichScheduler(subscribeOnWhichScheduler);
        return registerEvent(eventBuilder);
    }

    @Override
    public void postStick(@NonNull Object stickObject) {
        if (mStickEvents == null) {
            mStickEvents = new ConcurrentHashMap();
        }
        mStickEvents.put(stickObject, stickObject);
        post(stickObject);
    }


    @Override
    public void unRegisterEvent(@NonNull Disposable subscriber) {
        if (!hasSubscribes()) return;
        if (mBus == null) return;
        if (subscriber == null) return;
        subscriber.dispose();
    }

    @Override
    public void removeStickEvent(@NonNull Object stickEvent) {
        if (stickEvent == null) return;
        if (mStickEvents == null || mStickEvents.size() <= 0) return;
        mStickEvents.remove(stickEvent);
    }

    @Override
    public void clearAllStickEvents() {
        if (mStickEvents == null || mStickEvents.size() <= 0) return;
        mStickEvents.clear();
    }

    @Override
    public Disposable registerEvent(RxBusEventBuilder eventBuilder) {
        Disposable disposable = null;
        if (eventBuilder == null) {
            return disposable;
        }
        String eventName = eventBuilder.getEventName();
        Consumer consumer = eventBuilder.getConsumer();
        Consumer error = eventBuilder.getError();
        Action complete = eventBuilder.getComplete();
        Scheduler subscribeOnWhichScheduler = eventBuilder.getSubscribeOnWhichScheduler();
        boolean isStick = eventBuilder.isStick();
        if (mBus == null) {
            Log.e(TAG, "rxbus init failed");
            return null;
        }
        if (consumer == null) {
            Log.e(TAG, "cannot register a null consumer called {" + eventName + "} in rxbus line");
            return null;
        }
        // 如果是有设置事件回调线程，则切换至回调线程
        if (subscribeOnWhichScheduler != null) {
            if (error == null) {
                disposable = mBus.observeOn(subscribeOnWhichScheduler).subscribe(consumer);
            } else if (complete == null) {
                disposable = mBus.observeOn(subscribeOnWhichScheduler).subscribe(consumer, error);
            } else {
                disposable = mBus.observeOn(subscribeOnWhichScheduler).subscribe(consumer, error, complete);
            }
        } else {// 如果没有设置回调线程，那么整条流水线则是一直的
            if (error == null) {
                disposable = mBus.subscribe(consumer);
            } else if (complete == null) {
                disposable = mBus.subscribe(consumer, error);
            } else {
                disposable = mBus.subscribe(consumer, error, complete);
            }
        }
        // 然后遍历当前所有的黏性事件，发送给当前的订阅者
        if (isStick) {
            if (mStickEvents != null && mStickEvents.size() > 0) {
                for (Map.Entry entry : mStickEvents.entrySet()) {
                    if (mBus != null) mBus.onNext(entry.getValue());
                }
            }
        }
        return disposable;
    }

    @Override
    public boolean hasSubscribes() {
        if (mBus != null) {
            return mBus.hasSubscribers();
        }
        return false;
    }


    @Override
    public Observable toObservable() {
        return mBus == null ? null : mBus.toObservable();
    }

    @Override
    public Flowable toFlowable() {
        return mBus;
    }
}
