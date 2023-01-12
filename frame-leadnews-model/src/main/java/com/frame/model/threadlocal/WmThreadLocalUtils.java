package com.frame.model.threadlocal;

import com.frame.model.wemedia.pojo.WmUser;

/*
 *@ClassName WmThreadLocalUtils
 *@Description 储存自媒体用户信息的用户登录信息
 *@Author LiuQi
 *@Date 2023/1/12 09:56
 *@Version 1.0
 */
public class WmThreadLocalUtils {
    private static final ThreadLocal<WmUser> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程中的用户信息
     * @param wmUser
     */
    public static void setUser(WmUser wmUser) {
        userThreadLocal.set(wmUser);
    }

    /**
     * 获取线程中的用户信息
     *
     * @return
     */
    public static WmUser getUser() {
        return userThreadLocal.get();
    }

    /**
     * 清空线程中的用户信息
     */
    public static void clear() {
        userThreadLocal.remove();
    }
}
