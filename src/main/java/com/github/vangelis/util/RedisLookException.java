package com.github.vangelis.util;

import lombok.Getter;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 自定异常，用于跑出指定异常
 *
 * @author 王杰
 * @date 2019-07-01 10:51
 */
public class RedisLookException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private int responseStatus;
    /**
     *  构建自定异常
     * @param msg 异常说明
     * @param responseStatus 需要返回的状态码 {@link HttpServletResponse#SC_METHOD_NOT_ALLOWED}
     */
    public RedisLookException(String msg,int responseStatus){
        super(msg);
        this.responseStatus = responseStatus;
    }
}
