package com.lpp.pprxbus.base.inter;

import androidx.annotation.NonNull;

/**
 * 定义事件总线的bus的发送接口
 * <p>仅有当发送的行为更改的时候，才能更改该接口</p>
 */
public interface IPostBus<MESSAGE> {
    /**
     * 发送一个普通的消息事件
     * @param data
     */
    public void post(@NonNull MESSAGE data);

    /**
     * 发送一个黏性的消息事件
     * @param data
     * @return
     */
    public void postStick(@NonNull MESSAGE data);

    /**
     * 移除某个黏性事件
     * @param data
     */
    public void removeStickEvent(@NonNull MESSAGE data);

    /**
     * 清空所有的黏性事件
     */
    public void clearAllStickEvents();
}
