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


    //-----user exception
    USER_NOT_FOUND(400, "USER_NOT_FOUND", "User not found"),
    EMAIL_WAS_REGISTER(400, "EMAIL_WAS_REGISTER", "Email was register"),
    TOKEN_NULL(400, "TOKEN_NULL", "Token null"),
    REFRESH_TOKEN_NULL(400, "REFRESH_TOKEN_NULL", "Refresh token null"),
    USER_NAME_TOKEN_NOT_MATCH(400, "USER_NAME_TOKEN_NOT_MATCH", "User name token not match"),
    USER_NOT_AUTHEN(400, "USER_NOT_AUTHEN", "User not authen"),
    REFRESH_TOKEN_NOT_VALID(400, "REFRESH_TOKEN_NOT_VALID", "Refresh token not valid"),


    //----user localization exception
    USER_LOCALIZATION_NOT_FOUND(400, "USER_LOCALIZATION_NOT_FOUND", "User localization not found"),
    LOGIN_FAIL(401, "LOGIN_FAILED", "Login failed"),

    //----country exception
    COUNTRY_EXITS(400, "COUNTRY_EXITS", "Country exits"),
    COUNTRY_NOT_FOUND(400, "COUNTRY_NOT_FOUND", "Country not found"),

    //----city exception
    CITY_EXITS(400, "CITY_EXITS", "City exits"),
    CITY_NOT_FOUND(400, "CITY_NOT_FOUND", "City not found"),

    //----category exception
    CATEGORY_EXITS(400, "CATEGORY_EXITS", "Category exits"),
    CATEGORY_NOT_FOUND(400, "CATEGORY_NOT_FOUND", "Category not found")
    ;

    private int status;
    private String errorCode;
    private String message;
}
