package com.simol.ouncommon.api;

public enum ErrorCode {
    BAD_REQUEST("0400", "Bad Request"),
    UNAUTHORIZED("0401", "Unauthorized"),
    FORBIDDEN("0403", "Forbidden"),
    NOT_FOUND("0404", "Not Found"),
    INTERNAL_SERVER_ERROR("0500", "Internal Server Error");
    
    public final String code;
    public final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
