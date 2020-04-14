package com.lpp.pprxbus.rxbus.build;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class RxPostBuilder implements IRxPostBuilder{
    private Consumer event;
    private Consumer error;
    private Action complete;
    private boolean isStick;
    private int eventType;
    private Scheduler threadMode;

    private RxPostBuilder(){}
    
    public static RxPostBuilder create(){
        return new RxPostBuilder();
    }
    public RxPostBuilder setEvent(Consumer event) {
        this.event = event;
        return this;
    }

    public RxPostBuilder setThreadMode(Scheduler threadMode) {
        this.threadMode = threadMode;
        return this;
    }

    public RxPostBuilder setError(Consumer error) {
        this.error = error;
        return this;
    }

    public RxPostBuilder setComplete(Action complete) {
        this.complete = complete;
        return this;
    }

    public RxPostBuilder setStick(boolean stick) {
        isStick = stick;
        return this;
    }

    public RxPostBuilder setEventType(int eventType) {
        this.eventType = eventType;
        return this;
    }

    @Override
    public Consumer getErrorDo() {
        return error;
    }

    @Override
    public Action getCompleteDo() {
        return complete;
    }

    @Override
    public Consumer getEvent() {
        return event;
    }

    @Override
    public boolean isStick() {
        return isStick;
    }

    @Override
    public Scheduler getThreadMode() {
        return threadMode;
    }

    @Override
    public int getEventType() {
        return eventType;
    }
}
