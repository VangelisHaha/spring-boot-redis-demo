package com.github.vangelis.util;

/**
 * 结果包装类
 *
 * @author 王杰
 * @date 2019-07-01 10:48
 */
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功标记
     */
    public final static Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    public final static  Integer FAIL = 1;
    @Getter
    @Setter
    private int code = SUCCESS;

    @Getter
    @Setter
    private String msg = "success";


    @Getter
    @Setter
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }
}