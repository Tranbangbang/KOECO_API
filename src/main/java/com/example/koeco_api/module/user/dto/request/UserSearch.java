package com.example.koeco_api.module.user.dto.request;

import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.*;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearch {
    private String userName;
    private String companyName;
    private String director;

    public void simpleValidate(){
        this.userName = userName.trim();
        this.companyName = companyName.trim();
        this.director = director.trim();

        Arrays.stream(this.getClass().getDeclaredFields()).forEach(it ->
        {
            try {
                it.set(this, it.toString().trim());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
