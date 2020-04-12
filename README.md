# rxbusdemo
这是一个基于RxJava书写的RxBus事件流sdk

## 实现了部分eventbus的事件总线的传输，线程的切换以及黏性事件的支持

具体使用如下：

```
    /**
     * 注册一个事件
     * 默认的响应事件发生在订阅的同一线程线
     * @param consumer
     * @return
     */
    public Disposable registerEvent(@NonNull Consumer consumer) ;

    /**
     * 注册一个事件
     * 并且指定响应事件的发生的所在线程
     * @param consumer
     * @param subscribeOnWhichScheduler {@link io.reactivex.rxjava3.schedulers.Schedulers}
     * @return
     */
    public Disposable registerEventWithScheduler(@NonNull Consumer consumer , Scheduler subscribeOnWhichScheduler) ;
    /**
     * 注册一个事件，带error的事件
     * @param consumer
     * @param error
     * @return
     */
    public Disposable registerEvent(@NonNull Consumer consumer,@NonNull Consumer error) ;

    /**
     * 注册一个事件，带error的事件
     * 并且指定响应事件的发生的所在线程
     * @param consumer
     * @param error
     * @param subscribeOnWhichScheduler {@link io.reactivex.rxjava3.schedulers.Schedulers}
     * @return
     */
    public Disposable registerEventWithScheduler(@NonNull Consumer consumer,@NonNull Consumer error,Scheduler subscribeOnWhichScheduler) ;


    /**
     * 注册一个完整的事件
     * @param consumer
     * @param error
     * @param complete
     * @return
     */
    public Disposable registerEvent(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete) ;

    /**
     * 注册一个完整的事件
     * @param consumer
     * @param error
     * @param complete
     * @param subscribeOnWhichScheduler {@link io.reactivex.rxjava3.schedulers.Schedulers}
     * @return
     */
    public Disposable registerEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete,Scheduler subscribeOnWhichScheduler) ;

    /**
     * 反注册
     * @param subscriber
     */
    public void unRegisterEvent(@NonNull Disposable subscriber);

    /**
     * 判断当前是否有订阅者
     * @return
     */
    public boolean hasSubscribes();

    /**
     * 发送一个消息事件
     * @param object
     */
    public void post(@NonNull Object object);

    /**
     * 获取RxBus实例自己进行RxJava操作
     * 将当前的RxBus的队列转换成Obserable
     * @return
     */
    public Observable toObservable();

    /**
     * 获取RxBus实例自己进行RxJava操作
     * 将当前的RxBus的队列转换成Flowable
     * @return
     */
    public Flowable toFlowable();

    /**
     * 发送一个黏性的事件流
     * @param stickObject
     * @return
     */
    public void postStick(@NonNull Object stickObject);

    /**
     * 注册一个黏性事件
     * @param consumer
     */
    public Disposable registerStickEvent(@NonNull Consumer consumer);

    /**
     * 注册一个黏性事件，并且设置接收的事件流所在的线程
     * @param consumer
     * @param subscribeOnWhichScheduler {@link Schedulers},{@link io.reactivex.rxjava3.android.schedulers.AndroidSchedulers}
     * @return
     */
    public Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, Scheduler subscribeOnWhichScheduler);

    /**
     * 注册一个带失败回调的黏性事件流
     * @param consumer
     * @param error
     * @return
     */
    public Disposable registerStickEvent(@NonNull Consumer consumer, @NonNull Consumer error);

    /**
     * 注册一个带失败回调的黏性事件，并且设置接收的事件流所在的线程
     * @param consumer
     * @param error
     * @param subscribeOnWhichScheduler {@link Schedulers},{@link io.reactivex.rxjava3.android.schedulers.AndroidSchedulers}
     * @return
     */
    public Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, Scheduler subscribeOnWhichScheduler);

    /**
     * 注册一个完成的黏性事件
     * @param consumer
     * @param error
     * @param complete
     * @return
     */
    public Disposable registerStickEvent(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete);

    /**
     * 注册一个带完整的黏性事件流，并且设置接收的事件流所在的线程
     * @param consumer
     * @param error
     * @param complete
     * @param subscribeOnWhichScheduler {@link Schedulers},{@link io.reactivex.rxjava3.android.schedulers.AndroidSchedulers}
     * @return
     */
    public Disposable registerStickEventWithScheduler(@NonNull Consumer consumer, @NonNull Consumer error, @NonNull Action complete,Scheduler subscribeOnWhichScheduler);

    /**
     * 取消该黏性的事件流
     */
    public void removeStickEvent(@NonNull Object stickEvent);

    /**
     * 清楚所有的黏性事件流
     */
    public void clearAllStickEvents();

    /**
     * 使用构建者注册一个eventBus
     * @param eventBuilder
     */
    public Disposable registerEvent(RxBusEventBuilder eventBuilder);
    
    // 记得在你的onDestroy里面取消事件的订阅
    public void onDestroy(){
      if(disposable != null) disposable.dispose();
    }
```
