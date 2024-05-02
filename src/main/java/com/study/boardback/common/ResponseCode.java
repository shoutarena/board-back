package com.study.boardback.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {

    // HTTP Status 200
    SUCCESS("SU", HttpStatus.OK.value(), "Success."),

    // HTTP Status 400
    VALIDATION_FAILED("VF", HttpStatus.BAD_REQUEST.value(), "Validation failed."),
    DUPLICATE_EMAIL("DE", HttpStatus.BAD_REQUEST.value(), "Duplicate email."),
    DUPLICATE_NICKNAME("DN", HttpStatus.BAD_REQUEST.value(), "Duplicate nickname."),
    DUPLICATE_TEL_NUMBER("DT", HttpStatus.BAD_REQUEST.value(), "Duplicate tel number."),
    NOT_EXISTED_MEMBER("NM", HttpStatus.BAD_REQUEST.value(), "This user does not exist."),
    NOT_EXISTED_BOARD("NB", HttpStatus.BAD_REQUEST.value(), "This board does not exist."),

    // HTTP Status 401
    SIGN_IN_FAIL("SF", HttpStatus.UNAUTHORIZED.value(), "Login information mismatch."),
    AUTHORIZATION_FAILED("AF", HttpStatus.UNAUTHORIZED.value(), "Authorization Failed."),

    // HTTP Status 403
    NO_PERMISSION("NP", HttpStatus.FORBIDDEN.value(), "Do not hav permission."),

    // HTTP Status 500
    DATABASE_ERROR("DBE", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Database error");

    private String code;
    private int httpStatus;
    private String message;

    ResponseCode(String code, int httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
