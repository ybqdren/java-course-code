package org.github.ybqdren.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/9 16:58
 * @package org.github.ybqdren.common.exception
 * @description
 **/

@RestController
public class RestGlobalException {

    /**
     * Handing Exception
     * @param req
     * @param e
     */
    @ExceptionHandler(value = {Exception.class})
    public void handleException(Exception e, HttpServletRequest req, HttpServletResponse rep){

    }


}
