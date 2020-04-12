package com.lpp.rxbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lpp.pprxbus.RxBus;
import com.lpp.pprxbus.builder.RxBusEventBuilder;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lpp-exbus-test";
    Disposable disposable,disposableWithScheduler,disposableWithStick,disposableWithBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_helloword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) (Math.random() * 100);
                if (i / 2 == 0){
                    RxBus.getDefaultBus().post("this is a normal rx bus data:" + i);
                }else{
                    RxBus.getDefaultBus().postStick("this is a stick rx bus data:" + i);
                }
            }
        });
        disposable = RxBus.getDefaultBus().registerEvent(new Consumer() {
            @Override
            public void accept(Object o) throws Throwable {
                Log.e(TAG,"normal event:" + o.toString());
            }
        });
        disposableWithScheduler = RxBus.getDefaultBus().registerEventWithScheduler(new Consumer() {
            @Override
            public void accept(Object o) throws Throwable {
                Toast.makeText(MainActivity.this,"Scheduler event:" + o.toString(),Toast.LENGTH_LONG).show();
                Log.e(TAG, "Scheduler event:" + o.toString() + ",Thread name:" + Thread.currentThread().getName());
            }
        }, AndroidSchedulers.mainThread());
        disposableWithStick = RxBus.getDefaultBus().registerStickEvent(new Consumer() {
            @Override
            public void accept(Object o) throws Throwable {
                Log.e(TAG,"stick event:" + o.toString());
            }
        });
        RxBusEventBuilder rxBusEventBuilder = RxBusEventBuilder
                .create()
                .setConsumer(new Consumer() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        Log.e(TAG,"builder event:" + o.toString());
                    }
                })
                .setError(new Consumer() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        // TODO error
                    }
                })
                .setComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.e(TAG,"completer with builder");
                    }
                })
                .setSubscribeOnWhichScheduler(AndroidSchedulers.mainThread())
                .setStick(false);
        disposableWithBuilder = RxBus.getDefaultBus().registerEvent(rxBusEventBuilder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.dispose();
        if (disposableWithScheduler != null) disposableWithScheduler.dispose();
        if (disposableWithStick != null) disposableWithStick.dispose();
        if (disposableWithBuilder != null) disposableWithBuilder.dispose();

    }
}
