package com.github.ybqdren.passbook.advice;

import com.github.ybqdren.passbook.vo.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 全局异常处理 </h1>
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorInfo<String> errorHandler(HttpServletRequest request , Exception err){
        ErrorInfo<String> info = new ErrorInfo<String>();
        info.setMessage(err.getMessage());
        info.setData("Do Not Have Return Data");
        info.setUrl(request.getRequestURI().toString());

        return info;
    }
}
