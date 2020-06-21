package com.desafio.codenation.resources.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private LocalDateTime timeStamp;
    private String msg;

    public StandardError(Integer status, LocalDateTime timeStamp, String msg) {
        super();
        this.status = status;
        this.timeStamp = timeStamp;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}