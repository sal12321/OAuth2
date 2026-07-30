package com.sal.Oauth2.security;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.
                    authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                    .oauth2Login(oauth -> oauth.defaultSuccessUrl("/", true));

//        http
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();

    }

    @Bean
    CommandLineRunner test(
            @Value("${GOOGLE_CLIENT_ID:NOT_FOUND}") String googleId,
            @Value("${GITHUB_CLIENT_ID:NOT_FOUND}") String githubId) {

        return args -> {
            System.out.println("GOOGLE_CLIENT_ID = " + googleId);
            System.out.println("GITHUB_CLIENT_ID = " + githubId);
        };
    }


}
