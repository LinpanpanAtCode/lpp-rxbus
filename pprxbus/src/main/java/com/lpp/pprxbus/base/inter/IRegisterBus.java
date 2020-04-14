package com.lpp.pprxbus.base.inter;

import androidx.annotation.NonNull;

import com.lpp.pprxbus.base.ibuilder.IPostBuilder;

/**
 * 定义bus总线的注册接口
 * 仅有当注册的行为更改才能更改该接口
 * T 代表注册的对象，通常该对象只要是object即可
 * R 代表注册结果的返回
 */
public interface IRegisterBus<EVENT,RESULT> {
    /**
     * 注册一个普通对象T，返回对应的注册结果
     * @param event
     * @return
     */
    public RESULT registerEvent(@NonNull EVENT event);

    /**
     * 注册一个黏性事件T，返回对应的注册结果R
     * @param stickEvent
     */
    public RESULT registerStickEvent(@NonNull EVENT stickEvent);


    /**
     * 注册一个IPostBuilder的消息
     * @param postBuilder
     */
    public RESULT registerEvent(@NonNull IPostBuilder postBuilder);

    /**
     * 取消事件的注册
     * @param eventResult
     */
    public void unRegisterEvent(@NonNull RESULT eventResult);
}
