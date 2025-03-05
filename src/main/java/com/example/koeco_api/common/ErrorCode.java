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
    ID_NOT_FOUNDaaaa(1000, "MEMBER-ERR-400", "ID_NOT_FOUND."),
    ;

    private int status;
    private String errorCode;
    private String message;
}
