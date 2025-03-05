package com.example.koeco_api.module.user_localizations.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.module.user_localizations.dto.request.UserLocalRegisRequest;
import com.example.koeco_api.module.user_localizations.dto.request.UserLocalUpdateRequest;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import com.example.koeco_api.module.user_localizations.service.IUserLocalService;
import com.example.koeco_api.utils.UtilsValue;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(UtilsValue.BASE_URL + "/auth")
public class UserLocalController {
    @Autowired
    private IUserLocalService userLocalService;

    @PostMapping("/add_userlocal")
    @Operation(summary = "(For test)" +
            "add user-localization")
    public DefaultRes<UserLocalResponse> create(@RequestBody UserLocalRegisRequest request) {
        return DefaultRes.<UserLocalResponse>builder()
                .data(userLocalService.register(request))
                .statusCode(200)
                .build();
    }

    @PutMapping("/edit_userlocal/{id}")
    @Operation(summary = """
            (For test) update user localization
            """)
    public DefaultRes<UserLocalResponse> update(@RequestBody UserLocalUpdateRequest request,
                                                @PathVariable Long id) {

//        var result = userLocalService.update(request, id);
        return DefaultRes.<UserLocalResponse>builder()
                .data(userLocalService.update(request, id))
//                .data(result)
                .statusCode(200)
                .build();
    }

    @DeleteMapping("/delete_userlocal/{id}")
    @Operation(summary = """
            (For test) delete user localization
            """)
    public DefaultRes<String> delete(@PathVariable Long id){
        userLocalService.delete(id);
        return DefaultRes.<String>builder()
                .data("Delete user localization ok")
                .statusCode(200)
                .build();
    }
}
