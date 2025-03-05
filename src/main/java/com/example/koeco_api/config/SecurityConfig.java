package com.example.koeco_api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.koeco_api.module.user.common.Role.ADMIN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] PERMIT_URL_ARRAY = {
            "/api/v1/auth/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/test",
            "/health",
            "/sso/**",
            "/api/v1/demo",
            "/api/v1/member-company/**",
            "/api/v1/**",
    };
    private final JwtAuthenticaionFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        if (Objects.equals(activeProfile, "local")) {
//            http
//                    .authorizeHttpRequests(req ->
//                            req.requestMatchers(PathRequest.toH2Console()).permitAll()); //h2-console
//        }
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .exceptionHandling((exceptionConfig) ->
//                        exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler)
//                )
                .authorizeHttpRequests(req ->
                                req.requestMatchers(PERMIT_URL_ARRAY)
                                        .permitAll()
                                        .requestMatchers("/api/v1/admin/**").hasAnyRole(
                                                ADMIN.name()
                                        )
                                        .anyRequest()
                                        .authenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
