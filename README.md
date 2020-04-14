# 小弟不才，第一次在GitHub上提交自己的代码，写的不好地方欢迎指正
# 由于还没开通自己的maven库，这里先没给出maven以及gradle的引用方式
# @email pplin_1993@qq.com

# rxbusdemo
这是一个基于RxJava书写的RxBus事件流sdk

## 实现了部分eventbus的事件总线的传输，线程的切换以及黏性事件的支持
首先，在你的项目里面引入对RxJava/RxAndroid的支持

```
implementation "io.reactivex.rxjava3:rxandroid:3.0.0"
```

其次在你需要的项目里面依赖对应的lpprxbus module
重新构建之后就可以使用啦~

```
     // 订阅一个事件
     Disposable disposable =  RxBusDo.getDefaultBus().getRxRegister().registerEvent(new Consumer() {
                @Override
                public void accept(Object o) throws Throwable {
                    Log.e(TAG,"normal event:" + o.toString());
                }
            });
        
     // 发送一个普通事件
     RxBusDo.getDefaultBus().getRxPoster().post("this is a normal rx bus data");
     // 发送一个黏性事件
     RxBusDo.getDefaultBus().getRxPoster().postStick("this is a stick rx bus data");

     // 记得在你的onDestroy里面取消事件的订阅
     public void onDestroy(){
        if(disposable != null) disposable.dispose();
     }
```

具体使用如下：

```

    /**
     * 使用构建者注册一个eventBus
     * @param eventBuilder
     */
    public Disposable registerEvent(IRxPostBuilder eventBuilder);

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
     * 清除所有的黏性事件流
     */
    public void clearAllStickEvents();

```
