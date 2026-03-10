package com.example.Proyecto_Empleados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login","/registro","/h2-console/**").permitAll()
.requestMatchers("/admin/**").hasRole("ADMIN")
.requestMatchers("/usuario/**").hasRole("EMPLEADO")
.anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .successHandler((request, response, authentication) -> {
                String rol = authentication.getAuthorities().iterator().next().getAuthority();
                if(rol.equals("ROLE_ADMIN")) {
                    response.sendRedirect("/admin/usuarios");
                } else {
                    response.sendRedirect("/usuario/perfil");
                }
            })
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login")
        );

    return http.build();
}
}