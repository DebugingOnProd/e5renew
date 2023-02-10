package org.lhq.e5renew.entity;

import lombok.Getter;

@Getter
public enum APiCode {
    TOKEN_LENGTH_IS_INCORRECT(10000, "token长度不正确"),
    TOKEN_DOES_NOT_EXIST(10000, "token不存在"),
    OUTLOOK_NAME_NOT_NULL(10002, "outlook名称不能为空!");

    private int code;
    private String msg;

    APiCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
