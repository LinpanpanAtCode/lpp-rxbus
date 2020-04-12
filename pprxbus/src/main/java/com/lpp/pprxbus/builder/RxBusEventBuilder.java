package com.lpp.pprxbus.builder;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * 构建一个RxBusEvent基础参数
 *
 * @author linpanpan
 * @date 2020/4/11
 */
public class RxBusEventBuilder {
    private String eventName;
    private Consumer consumer;
    private Consumer error;
    private Action complete;
    private Scheduler subscribeOnWhichScheduler;
    private boolean isStick;

    public String getEventName() {
        return eventName;
    }
    private RxBusEventBuilder(){super();}

    public static RxBusEventBuilder create(){
        return new RxBusEventBuilder();
    }
    public RxBusEventBuilder setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public RxBusEventBuilder setConsumer(Consumer consumer) {
        this.consumer = consumer;
        return this;
    }

    public Consumer getError() {
        return error;
    }

    public RxBusEventBuilder setError(Consumer error) {
        this.error = error;
        return this;
    }

    public Action getComplete() {
        return complete;
    }

    public RxBusEventBuilder setComplete(Action complete) {
        this.complete = complete;
        return this;
    }

    public Scheduler getSubscribeOnWhichScheduler() {
        return subscribeOnWhichScheduler;
    }

    public RxBusEventBuilder setSubscribeOnWhichScheduler(Scheduler subscribeOnWhichScheduler) {
        this.subscribeOnWhichScheduler = subscribeOnWhichScheduler;
        return this;
    }

    public boolean isStick() {
        return isStick;
    }

    public RxBusEventBuilder setStick(boolean stick) {
        isStick = stick;
        return this;
    }
}
