package ru.example.hometask_8.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = false, jsr250Enabled = true)
public class SecurityPolicyConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    //.formLogin(form -> form.loginPage("/web/login"))
                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                            authorizationManagerRequestMatcherRegistry
                                    .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "*web/delete**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "*web/create**").hasRole("ADMIN")
                                    .requestMatchers("*/web/admin**").hasAnyRole("ADMIN")
                                    .requestMatchers("*/web/home**").hasAnyRole("USER", "ADMIN")
                                    .requestMatchers("*/web/login**").permitAll()
                                    .anyRequest().authenticated()
                    )

//                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                            authorizationManagerRequestMatcherRegistry
//                                    .requestMatchers( "/web/login").permitAll()
//                    )
//                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                            authorizationManagerRequestMatcherRegistry
//                                    .requestMatchers("/web/delete").hasRole("ADMIN")
//                                    .requestMatchers( "/web/create").hasRole("ADMIN")
//                                    .requestMatchers( "/web/admin").hasRole("ADMIN")
//                    )
//                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                            authorizationManagerRequestMatcherRegistry
//                                    .requestMatchers("/web/home").hasAnyRole("ADMIN", "USER")
//                    )


                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return http.build();
    }

}
