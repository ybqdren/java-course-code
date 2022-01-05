package org.github.ybqdren.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/5 17:31
 * @package org.github.ybqdren.common.exception
 * @description
 **/
public class BadRequestException extends RuntimeException{
    private Integer status = 9999;

    public BadRequestException(String msg){super(msg);}

    public BadRequestException(HttpStatus status,String msg){
        super(msg);
        this.status = status.value();
    }
}
