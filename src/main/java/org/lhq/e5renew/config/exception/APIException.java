package org.lhq.e5renew.config.exception;

import org.lhq.e5renew.entity.APiCode;

public class APIException extends RuntimeException{
    private int code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1002, msg);
    }

    public APIException(APiCode code) {
        this(code.getCode(), code.getMsg());
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
