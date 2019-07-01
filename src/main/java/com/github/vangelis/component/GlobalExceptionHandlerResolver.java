package com.github.vangelis.component;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.json.JSONUtil;
import com.github.vangelis.util.R;
import com.github.vangelis.util.RedisLookException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 全局异常处理器
 *
 * @author 王杰
 * @date 2019-07-01 10:47
 */

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandlerResolver {

    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleGlobalException(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.builder()
                .msg(e.getLocalizedMessage())
                .code(R.FAIL)
                .build();
    }

    /**
     * 拦截处理自定义异常
     *
     * @param exception 异常信息
     */
    @ExceptionHandler(RedisLookException.class)
    public void handleApiException(RedisLookException exception) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();
        assert response != null;
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setStatus(exception.getResponseStatus());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        R res = R.builder()
                .msg(exception.getMessage())
                .code(R.FAIL)
                .build();
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSONUtil.toJsonStr(res));
            writer.flush();
        } catch (IOException e) {
            log.warn("Error: Response printJson faild, stackTrace: {}", ExceptionUtil.stacktraceToString(e));
        }
    }
}
