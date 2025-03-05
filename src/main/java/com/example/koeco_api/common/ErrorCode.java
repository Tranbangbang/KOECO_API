package com.example.koeco_api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404, "COMMON-ERR-404", "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR"),
    ID_DUPLICATION(400, "MEMBER-ERR-400", "ID_DUPLICATION."),

    ID_NOT_FOUND(400, "MEMBER-ERR-400", "ID_NOT_FOUND."),


    //-----user exception
    USER_NOT_FOUND(400, "USER_NOT_FOUND", "User not found"),
    EMAIL_WAS_REGISTER(400, "EMAIL_WAS_REGISTER", "Email was register"),


    //----user localization exception
    USER_LOCALIZATION_NOT_FOUND(400, "USER_LOCALIZATION_NOT_FOUND", "User localization not found")
    ;

    private int status;
    private String errorCode;
    private String message;
}
