package com.example.Proyecto_Empleados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
// Configuración de seguridad para la aplicación
//@Bean devuelve un objeto que Spring va a usar para filtrar y proteger las rutas de tu aplicación

@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//Desactiva la protección CSRF y las opciones de frame para permitir el acceso a la consola H2
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