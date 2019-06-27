package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;
    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = new Date().getTime();
    }
}
